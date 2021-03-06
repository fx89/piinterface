package com.desolatetimelines.piinterface.service;

import static com.desolatetimelines.piinterface.service.Constants.BUTTON_TYPE_PIN_NAME;
import static com.desolatetimelines.piinterface.service.Constants.GROUP_TYPE_SEQUENTIAL;
import static com.desolatetimelines.piinterface.service.Constants.GROUP_TYPE_SIMULTANEOUS;
import static com.desolatetimelines.piinterface.service.Constants.NOTIFICATION_TYPE_ERROR;
import static com.desolatetimelines.piinterface.service.Constants.NOTIFICATION_TYPE_INFO;
import static com.desolatetimelines.piinterface.service.Constants.NOTIFICATION_TYPE_WARNING;
import static com.desolatetimelines.piinterface.service.Constants.PIN_OPERATING_MODE_PUSHBUTTON;
import static com.desolatetimelines.piinterface.service.utils.IpAddressRangesUtil.getIpAddessRangeType;
import static com.desolatetimelines.piinterface.service.utils.IpAddressRangesUtil.parseIpAddressDigit;
import static com.desolatetimelines.piinterface.service.utils.IpAddressRangesUtil.toIpAddressDigit;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.StreamSupport;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desolatetimelines.piinterface.model.IpAddressRange;
import com.desolatetimelines.piinterface.model.PiInstance;
import com.desolatetimelines.piinterface.model.Pin;
import com.desolatetimelines.piinterface.model.PinGroup;
import com.desolatetimelines.piinterface.model.PinGroupPin;
import com.desolatetimelines.piinterface.model.PinOperatingMode;
import com.desolatetimelines.piinterface.model.UiButton;
import com.desolatetimelines.piinterface.piclient.PiClientService;
import com.desolatetimelines.piinterface.piclient.model.PIInstance;
import com.desolatetimelines.piinterface.piclient.model.PIInstancePin;
import com.desolatetimelines.piinterface.service.exception.CorruptedRegistryException;
import com.desolatetimelines.piinterface.service.exception.PiInterfaceServiceException;
import com.desolatetimelines.piinterface.service.model.Notification;
import com.desolatetimelines.piinterface.service.model.PinGroupWithState;
import com.desolatetimelines.piinterface.service.model.PinWithState;
import com.desolatetimelines.piinterface.service.model.UiButtonWithState;
import com.desolatetimelines.piinterface.service.utils.IpAddressRangesUtil.IpAddressType;

import lombok.Getter;

@Component
public class PiInterfaceService {

	@Autowired
	@Getter
	private PiInterfaceDataService dataService;

	@Autowired
	@Getter
	private PiClientService clientService;

	@Autowired
	@Getter
	private NotificationService notificationService;

	@Getter
	private final Map<Long, UiButtonWithState> uiButtonsRepository = new ConcurrentHashMap<>();

	@Getter
	private final Map<Long, PinGroupWithState> pinGroupsRepository = new ConcurrentHashMap<>();

	@Getter
	private final Map<Long, PinWithState> pinsRepository = new ConcurrentHashMap<>();

	private boolean isWorking = false;


	/**
	 * Initialize the service
	 */
	@PostConstruct
	private void init() {

	}

	@Transactional
	public void resyncPiInstance(Long piInstanceId) {
		if (piInstanceId == null || piInstanceId < 0) {
			throw new IllegalArgumentException("PI instance ID must be positive.");
		}

		queueInfo("Working", "Retrieving PI instance from the registry");
		PiInstance registeredInstance = dataService.getPiInstancesRepository()
				.findById(piInstanceId)
				.orElseThrow(() -> new IllegalArgumentException("Pi instance with id [" + piInstanceId + "] not registered"));

		queueInfo("Working", "Querying IP address [" + registeredInstance.getLastRegisteredAddress() + "]");
		PIInstance instanceInfo = clientService.getInfo(registeredInstance.getLastRegisteredAddress());

		if (instanceInfo == null) {
			queueInfo("Working", "The PI instance is offline");
			registeredInstance.setIsOffline(true);
		} else {
			registeredInstance.setIsOffline(false);
		}

		queueInfo("Working", "Updating the registry");
		registeredInstance = dataService.getPiInstancesRepository().save(registeredInstance);

		synchronizePiInstancePins(registeredInstance);
	}

	/**
	 * Verifies that the PI instances recorded in the database are still valid (i.e.
	 * IP addresses are correct). Does it by querying the configured IP range and
	 * reconciling the names of the PI instances with the ones recorded in the
	 * database. Adds any new PI instances which might be discovered
	 */
	@Transactional
	public void synchronizePiInstances() {
		// Spring aspect? Nah :P

		if (isWorking) {
			throw new PiInterfaceServiceException("The sync operation is already in progress. Please try again later.");
		}

		try {
			isWorking = true;
			syncPiInstances();
			isWorking = false;
		} catch (Exception exc) {
			isWorking = false;
			throw exc;
		}
	}

	/**
	 * Verifies that the PI instances recorded in the database are still valid (i.e.
	 * IP addresses are correct). Does it by querying the configured IP range and
	 * reconciling the names of the PI instances with the ones recorded in the
	 * database. Adds any new PI instances which might be discovered
	 */
	private void syncPiInstances() {
		// Inform end-user that the process has begun
		clearQueue();
		queueInfo("Processing", "Beginning to query the defined address ranges");
		queueWarning("Warning", "This operation might take a while");

		// Get address ranges
		Iterable<IpAddressRange> addressRanges = dataService.getIpAddressRangesRepository().findAll();

		// Warn if there are no ranges defined and then exit
		if (addressRanges == null || addressRanges.iterator().hasNext() == false) {
			queueWarning("Missing address ranges", "There are no address ranges defined");
			return;
		}

		// Find all PI instances in the given IP ranges
		List<PIInstance> allDetectedInstances = StreamSupport.stream(addressRanges.spliterator(), true)
			.flatMap(range -> queryIpRange(range).stream())
			.collect(toList());

		// Warn if no PI instances were found in the given ranges and then exit
		if (allDetectedInstances == null || allDetectedInstances.size() == 0) {
			queueWarning("Instances not found", "No PI instances were found in the given address ranges");
			allDetectedInstances = new ArrayList<>(); // Go on so that the rest of the code may set instances to OFFLINE
		}

		// Get all currently registered instances
		List<PiInstance> allRegisteredInstances
			= StreamSupport.stream(dataService.getPiInstancesRepository().findAll().spliterator(), true)
				.collect(toList());

		// Initialize a list of instances to save (either newly detected instances or
		// instances which have changed addresses)
		List<PiInstance> instancesToSave = new ArrayList<>();

		queueInfo("Working", "Updating registry");

		// Reconcile the detected instances with the registered instances
		for(PIInstance detectedInstance : allDetectedInstances) {
			// 1) registered instances which haven't changed addresses
			//			>>> nothing to do here
			// 2) registered instances which have changed addresses
			//			>>> update the addresses of the registered instances and save them (bulk)
			// 3) registered instances which have gone offline (name no longer found in the given ranges)
			//			>>> set the status to OFFLINE and save (bulk)
			// 4) new instances which need to be registered
			//			>>> register and save (bulk)

			// See if there is any registered instance matching the detected instance
			PiInstance registeredInstance
				= allRegisteredInstances.stream()
					.filter(regInst -> regInst.getName().equalsIgnoreCase(detectedInstance.getInstanceName()))
					.findFirst()
					.orElse(null);

			if (registeredInstance == null) {
				// Instance is new and needs to be registered
				PiInstance newInstance = new PiInstance();
				newInstance.setName(detectedInstance.getInstanceName());
				newInstance.setLastRegisteredAddress(detectedInstance.getAddress());
				newInstance.setIsOffline(false);
				instancesToSave.add(newInstance);
			} else {
				if (!registeredInstance.getLastRegisteredAddress().equals(detectedInstance.getAddress())) {
					// Instance exists, but its address has changed and needs to be updated
					registeredInstance.setLastRegisteredAddress(detectedInstance.getAddress());
					registeredInstance.setIsOffline(false); // In case the instance was offline and now has been found again
					instancesToSave.add(registeredInstance);
				}
			}
		}

		// Check for registered instances which are no longer being detected
		for (PiInstance regInstance : allRegisteredInstances) {
			// Attempt to identify the registered instance within the list of detected instances
			PIInstance detInstance
				= allDetectedInstances.stream()
					.filter(detInst -> detInst.getInstanceName().equalsIgnoreCase(regInstance.getName()))
					.findFirst()
					.orElse(null);

			// If the registered instance is no longer detected, mark it as gone
			if (detInstance == null) {
				regInstance.setIsOffline(true);
				instancesToSave.add(regInstance);
			}
		}

		// Save what needs to be saved (if anything)
		if (instancesToSave.size() > 0) {
			dataService.getPiInstancesRepository().saveAll(instancesToSave);
		}

		// Synchronize the pins for all online instances
		Iterable<PiInstance> instances = dataService.getPiInstancesRepository().findAll();
		instances.forEach(instance -> {
			if (instance.getIsOffline() == false) {
				synchronizePiInstancePins(instance);
			}
		});

		// Update the UI buttons cache
		queueInfo("Processing", "Updating UI buttons cache");
		instances.forEach(instance -> {
			uiButtonsRepository.forEach((k,v) -> {
				if (v.getLinkedToPin() != null && v.getLinkedToPin().getPiInstance().getName().equalsIgnoreCase(instance.getName())) {
					v.getLinkedToPin().setPiInstance(instance);
				}
			});
		});

		// Inform end-user that the process has ended
		queueInfo("Processing", "Processing finished");
	}

	private List<PIInstance> queryIpRange(IpAddressRange ipRange) {
		// Make a list of all the addresses
		// This is required so that ParallelStream may be used to query multiple addresses at the same time
		List<String> ipAddresses = new ArrayList<>();
		try {
			IpAddressType rangeType = getIpAddessRangeType(ipRange);
			int rangeStart = parseIpAddressDigit(ipRange.getRangeStart());
			int rangeEnd = parseIpAddressDigit(ipRange.getRangeEnd());
			for (int r = rangeStart; r <= rangeEnd ; r++) {
				ipAddresses.add(ipRange.getPrefix() + toIpAddressDigit(r, rangeType));
			}
		} catch (IllegalArgumentException iaEx) {
			queueWarning("Cannot process address range", iaEx.getMessage());
	 		return new ArrayList<>();
		}
		
		// Initialize a list of PI instances to return
		final List<PIInstance> detectedInstances = new ArrayList<>();
		
		// Query the IP addresses in parallel and stream the result
		// into the previously initialized list
		ipAddresses.parallelStream().forEach(addr -> {
				queueInfo("Working", "Scanning [" + addr + "]");
				PIInstance clientInfo = clientService.getInfo(addr);
				// NULL will be returned:
				//		1) There is nothing listening at the queried address
				//		2) There is something listening at the queried address, but the response is inadequate
				//		3) Other factors which might prevent querying the address
				// In this case, one should simply hop over the address and try the next one
				if (clientInfo != null) {
					detectedInstances.add(clientInfo);
					queueWarning("Instance found", "Found [" + clientInfo.getInstanceName() + "] on [" + clientInfo.getAddress() + "]"); // Make it a warning, to stand out in the log
				}
		});

		// Return the list
		return detectedInstances;
	}

	@Transactional
	public void synchronizePiInstancePins(Long piInstanceId) {
		if (piInstanceId == null || piInstanceId < 0) {
			throw new IllegalArgumentException("PI instance ID must be positive.");
		}

		PiInstance instance
			= dataService.getPiInstancesRepository().findById(piInstanceId)
				.orElseThrow(() -> new IllegalArgumentException("Instance with id [" + piInstanceId + "] not found."));

		synchronizePiInstancePins(instance);
	}

	@Transactional
	public void synchronizePiInstancePins(PiInstance instance) {
		if (instance == null || instance.getId() == null) {
			throw new IllegalArgumentException("Invalid instance.");
		}

		queueInfo("Working", "Synchronizing [" + instance.getName() + "]");

		PIInstance instanceInfo = clientService.getInfo(instance.getLastRegisteredAddress());

		// Queue warning if the instance is offline
		if (instanceInfo == null) {
			queueWarning("Instance offline", "The PI instance at [" + instance.getLastRegisteredAddress() + "] is offline.");
			return;
		}

		synchronizePiInstancePins(instance, instanceInfo);
	}

	private void synchronizePiInstancePins(PiInstance instance, PIInstance instanceInfo) {
		// Raise exception if there's any problem
		if (instance == null) {
			throw new IllegalArgumentException("No registered instance was provided");
		}

		if (instanceInfo == null) {
			throw new IllegalArgumentException("No detected instance was provided");
		}

		// Get the pushbutton operating mode
		PinOperatingMode pushbutonOperatingMode
			= dataService.getPinOperatingModesRepository()
				.findOneByName("PUSHBUTTON")
				.orElseThrow(() -> new CorruptedRegistryException("The registry is missing the PUSHBUTTON operating mode"));
		
		// Get the list of saved pins
		List<Pin> savedPins = dataService.getPinsRepository().findAllByPiInstanceId(instance.getId());

		// Get the list of available pins
		List<PIInstancePin> availablePins
			= StreamSupport.stream(instanceInfo.getAvailablePins().spliterator(), false)
				.collect(toList());

		// Initialize the list of pins to be saved
		final List<Pin> pinsToBeSaved = new ArrayList<>();
		
		// Compile a list of existing pins while also updating their properties
		// Compile a list of removed pins and set their "isAvailable" properties to FALSE
		savedPins.stream().forEach(savedPin -> {
			PIInstancePin availablePin
				= availablePins.stream()
					.filter(avPin -> avPin.getBoardId() != null && avPin.getBoardId().equals(savedPin.getBoardId()))
					.findFirst()
					.orElse(null);

			if (availablePin == null) {
				savedPin.setIsAvailable(false);
			} else {
				savedPin.setGpioId(availablePin.getGpioId());
				savedPin.setName(availablePin.getName());
				savedPin.setIsAvailable(true);
			}

			pinsToBeSaved.add(savedPin);
		});

		// Compile a list of newly added pins
		availablePins.forEach(availablePin -> {
			Pin savedPin
				= savedPins.stream()
					.filter(svPin -> svPin.getBoardId() != null && svPin.getBoardId().equals(availablePin.getBoardId()))
					.findFirst()
					.orElse(null);

			if (savedPin == null) {
				Pin newPin = new Pin();
				newPin.setPiInstance(instance);
				newPin.setBoardId(availablePin.getBoardId());
				newPin.setGpioId(availablePin.getGpioId());
				newPin.setIsAvailable(true);
				newPin.setName(availablePin.getName());
				newPin.setOperatingMode(pushbutonOperatingMode);
				pinsToBeSaved.add(newPin);
			}
		});

		// Save all pins
		dataService.getPinsRepository().saveAll(pinsToBeSaved);
	}

	/**
	 * Queues a message of type INFO into the notification service
	 */
	public void queueInfo(String title, String message) {
		notificationService.queueNotification(new Notification(NOTIFICATION_TYPE_INFO, title, message));
	}

	/**
	 * Queues a message of type WARNING into the notification service
	 */
	public void queueWarning(String title, String message) {
		notificationService.queueNotification(new Notification(NOTIFICATION_TYPE_WARNING, title, message));
	}

	/**
	 * Queues a message of type ERROR into the notification service
	 */
	public void queueError(String title, String message) {
		notificationService.queueNotification(new Notification(NOTIFICATION_TYPE_ERROR, title, message));
	}

	/**
	 * Clears the notifications queue
	 */
	public void clearQueue() {
		notificationService.clear();
	}

	@Transactional
	public PinGroupPin mapPinToPinGroup(Long pinId, Long groupId) {
		// Get the pin or throw exception
		Pin pin = getDataService().getPinsRepository().findById(pinId)
				.orElseThrow(() -> new PiInterfaceServiceException("Pin not found"));

		// Get the group or throw exception
		PinGroup pinGroup = getDataService().getPinGroupsRepository().findById(groupId)
				.orElseThrow(() -> new PiInterfaceServiceException("Group not found"));

		// Get the existing mappings
		List<PinGroupPin> existingMaps = getDataService().getPinGroupPinsRepository()
				.findAllByPinGroupId(groupId);

		// Check that the pin is not already mapped
		existingMaps.forEach(map -> {
			if (map.getPin().getId().equals(pinId)) {
				throw new PiInterfaceServiceException("Pin is already mapped to group");
			}
		});

		// Get the first available order number for the pin in the group
		Integer order = existingMaps.stream()
				.map(a -> a.getOrder())
				.max((a,b) -> { return a == null ? -1 : a.compareTo(b); })
				.orElse(0)
			+ 1;

		// Create the new mapping
		PinGroupPin newMap = new PinGroupPin(null, pinGroup, pin, order);

		// Save and return the new mapping
		return getDataService().getPinGroupPinsRepository().save(newMap);
	}

	/**
	 * Switches the order of the identified pin with that of the pin having
	 * the greatest order that's smaller than that the order of the identified pin.
	 * If there is no such pin, the order of the identified pin remains the same.
	 */
	@Transactional
	public PinGroupPin movePinUpInGroup(Long pinGroupPinId) {
		// Get the pin / group map having the given id
		PinGroupPin identifiedPin = dataService.getPinGroupPinsRepository()
				.findById(pinGroupPinId)
				.orElseThrow(() -> new PiInterfaceServiceException("Unable to identify pin / group map"));

		// Get all the pins in the same group as the identified pin
		List<PinGroupPin> allGroupPins = dataService.getPinGroupPinsRepository()
				.findAllByPinGroupId(identifiedPin.getPinGroup().getId());

		// Find the previous pin
		final int identifiedPinOrder = identifiedPin.getOrder();
		PinGroupPin previousPin = allGroupPins.stream()
				.filter(pgp -> pgp.getOrder() < identifiedPinOrder)
				.sorted((pgp1, pgp2) -> -(pgp1.getOrder().compareTo(pgp2.getOrder()))) // add a minus to reverse the sort order
				.findFirst().orElse(null);

		// If there is a previous pin, then switch the order and save
		if (previousPin != null) {
			int tmp = previousPin.getOrder();
			previousPin.setOrder(identifiedPin.getOrder());
			identifiedPin.setOrder(tmp);
			previousPin = dataService.getPinGroupPinsRepository().save(previousPin);
			identifiedPin = dataService.getPinGroupPinsRepository().save(identifiedPin);
		}

		// Return the value anyway
		return identifiedPin;
	}

	/**
	 * Switches the order of the identified pin with that of the pin having
	 * the smallest order that's greater than that the order of the identified pin.
	 * If there is no such pin, the order of the identified pin remains the same.
	 */
	@Transactional
	public PinGroupPin movePinDownInGroup(Long pinGroupPinId) {
		// Get the pin / group map having the given id
		PinGroupPin identifiedPin = dataService.getPinGroupPinsRepository()
				.findById(pinGroupPinId)
				.orElseThrow(() -> new PiInterfaceServiceException("Unable to identify pin / group map"));

		// Get all the pins in the same group as the identified pin
		List<PinGroupPin> allGroupPins = dataService.getPinGroupPinsRepository()
				.findAllByPinGroupId(identifiedPin.getPinGroup().getId());

		// Find the next pin
		final int identifiedPinOrder = identifiedPin.getOrder();
		PinGroupPin nextPin = allGroupPins.stream()
				.filter(pgp -> pgp.getOrder() > identifiedPinOrder)
				.sorted((pgp1, pgp2) -> (pgp1.getOrder().compareTo(pgp2.getOrder())))
				.findFirst().orElse(null);

		// If there is a previous pin, then switch the order and save
		if (nextPin != null) {
			int tmp = nextPin.getOrder();
			nextPin.setOrder(identifiedPin.getOrder());
			identifiedPin.setOrder(tmp);
			nextPin = dataService.getPinGroupPinsRepository().save(nextPin);
			identifiedPin = dataService.getPinGroupPinsRepository().save(identifiedPin);
		}

		// Return the value anyway
		return identifiedPin;
	}

	/**
	 * Stores UI buttons and their states in a local repository,
	 * so that the states can be accessed without having to query the PI instances.
	 * This functionality is not critical, but it helps the UI show the correct
	 * status of switches after bootup.
	 */
	@PostConstruct
	private void buildButtonsRepositoryInformation() {

		final Map<Long, PIInstance> piInstances = new HashMap<>();
		
		// UI buttons will be split into two collections:
		//    1) Buttons linked to groups - the initial state will be 0, as groups may not be switches
		//    2) Buttons linked to pins - the states of the buttons coincide with the states of the pins as reported by their respective PI instances
		// Work in multiple parallel threads to minimize the wait time caused by potentially non-responsive PI instances
		//
		StreamSupport.stream(dataService.getUiButtonsRepository().findAll().spliterator(), true)
		.forEach(uiButton -> {
			if (uiButton.getType().getName().equals(BUTTON_TYPE_PIN_NAME)) {
				// Case (2) - add the button to the repository with the state reported by the
				// PI instance
				
				// Get the instance ID (to be used later)
				Long piInstanceId = uiButton.getLinkedToPin().getPiInstance().getId();

				// See if the instance was already queried
				PIInstance piInstance = piInstances.get(piInstanceId);

				// If it wasn't, then it must be queried
				if (piInstance == null) {
					// Query the instance
					piInstance = clientService.getInfo(uiButton.getLinkedToPin().getPiInstance().getLastRegisteredAddress());

					// If the instance is non-responsive, create a dummy instance
					// This will prevent querying the non-responsive instance for each pin
					if (piInstance == null) {
						piInstance = new PIInstance();
						piInstance.setAvailablePins(new ArrayList<>());
					}

					// Register the queried instance info, so it doesn't have to be queried
					// during future iterations
					piInstances.put(piInstanceId, piInstance);
				}
				
				// Get the pin state from the instance info
				// If the pin is not in the instance info, then presume the state to be 0
				int state
					= StreamSupport.stream(piInstance.getAvailablePins().spliterator(), false)
						.filter(detectedPin -> detectedPin.getBoardId().equals(uiButton.getLinkedToPin().getBoardId()))
						.findAny()
						.map(detectedPin -> detectedPin.getCurrentStatus())
						.orElse(0);

				// Finally, add the pin to the repository
				uiButtonsRepository.put(uiButton.getId(), new UiButtonWithState(uiButton, state));
			} else {
				// Case (1) - add the button to the repository and presume the state to be 0,
				// because the state can never be 1 for buttons linked to pin groups
				uiButtonsRepository.put(uiButton.getId(), new UiButtonWithState(uiButton, 0));
			}
		});
	}

	public UiButtonWithState clickButton(Long buttonId) {
		UiButtonWithState theButton = resolveButton(buttonId);

		if (theButton.getType().getName().equals(BUTTON_TYPE_PIN_NAME)) {
			theButton.setState(touchPin(theButton.getLinkedToPin(), theButton.getState(), theButton.getTargetPinState()));
		} else {
			touchGroup(theButton.getLinkedToPinGroup(), theButton.getTargetPinState());
			theButton.setState(0);
		}

		return theButton;
	}

	private void touchGroup(PinGroup group, Long targetPinState) {
		PinGroupWithState pinGroup = resolvePinGroup(group.getId());

		List<PinGroupPin> groupPins = dataService.getPinGroupPinsRepository().findAllByPinGpioId(group.getId());

		if (groupPins == null || groupPins.size() == 0) {
			throw new PiInterfaceServiceException("The group [" + group.getName() + "] does not contain any pins");
		}

		if (pinGroup.getType().getName().equalsIgnoreCase(GROUP_TYPE_SEQUENTIAL)) {
			touchGroupSequential(pinGroup, groupPins, targetPinState);
			return;
		}

		if (pinGroup.getType().getName().equalsIgnoreCase(GROUP_TYPE_SIMULTANEOUS)) {
			touchGroupSimultaneous(pinGroup, groupPins, targetPinState);
			return;
		}

		throw new PiInterfaceServiceException("Group type [" + pinGroup.getType().getName() + "] is not implementend");
	}

	private void touchGroupSequential(PinGroupWithState pinGroup, List<PinGroupPin> groupPins, Long targetPinState) {
		PinGroupPin nextPin = groupPins.stream()
				.filter(pgp -> pgp.getOrder() > pinGroup.getCurrentOrder())
				.sorted((pgp1, pgp2) -> pgp1.getOrder().compareTo(pgp2.getOrder()))
				.findFirst().orElse(null);

		if (nextPin == null) {
			nextPin = groupPins.stream()
					.sorted((pgp1, pgp2) -> pgp1.getOrder().compareTo(pgp2.getOrder()))
					.findFirst().orElse(null);
		}

		if (nextPin.getPin().getIsAvailable()) {
			clickPin(nextPin.getPin(), targetPinState);
		} else {
			throw new PiInterfaceServiceException(
				"The pin [" + nextPin.getPin().getPiInstance().getName() + "/" + nextPin.getPin().getName() + "] registers as no longer available. Please re-sync the PI instance."
			);
		}

		pinGroup.setCurrentOrder(nextPin.getOrder());
	}

	private void touchGroupSimultaneous(PinGroupWithState pinGroup, List<PinGroupPin> groupPins, Long targetPinState) {
		// Check the availability of all pins
		groupPins.forEach(groupPin -> {
			if (groupPin.getPin().getIsAvailable() == false) {
				throw new PiInterfaceServiceException(
						"The pin [" + groupPin.getPin().getPiInstance().getName() + "/" + groupPin.getPin().getName() + "] registers as no longer available. Please re-sync the PI instance."
				);
			}
		});

		// Click the pins
		groupPins.forEach(groupPin -> {
			clickPin(groupPin.getPin(), targetPinState);
		});
	}

	private int touchPin(Pin pin, int buttonState, Long targetPinState) {
		if (pin.getIsAvailable()) {
			PIInstancePin result = null;
			
			if (pin.getOperatingMode().getName().equals(PIN_OPERATING_MODE_PUSHBUTTON)) {
				result = clickPin(pin, targetPinState);
			} else {
				result = switchPin(pin, buttonState);
			}

			if (result == null) {
				throw new PiInterfaceServiceException("The PI instance [" + pin.getPiInstance().getName() + "] is unreachable");
			}

			return result.getCurrentStatus();
		} else {
			throw new PiInterfaceServiceException(
				"The pin [" + pin.getPiInstance().getName() + "/" + pin.getName() + "] registers as no longer available. Please re-sync the PI instance."
			);
		}
	}

	private PIInstancePin clickPin(Pin pin, Long targetPinState) {
		// Get the state of the pin or, if it doesn't exist, create a new one
		PinWithState pinWithState = resolvePinWithState(pin);

		// If the button attempts to set the target device to a given state
		if (targetPinState != null) {
			// Make sure the target state is valid (0 <= target state < pin states count)
			if (targetPinState < 0 || targetPinState >= pinWithState.getStatesCount()) {
				throw new PiInterfaceServiceException("Invalid target state. Check consistency with the states count of pin [" + pinWithState.getPiInstance().getName() + " / " + pinWithState.getName() + "].");
			}

			// Prepare the PI status to be returned
			PIInstancePin ret = null;

			// Keep clicking the pin until the device has reached the targeted state
			while (pinWithState.getCurrentState() != targetPinState) {
				ret = clickPinAndUpdateState(pinWithState);

				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
	 				throw new PiInterfaceServiceException("Unable to sleep: " + e.getMessage(), e);
				}
			}

			// Return the pin state
			if (ret == null) {
				// Attempting to switch to the same state as the current one
				//		=> no clicking and no state request from the PI
				return
					StreamSupport.stream(
						clientService
							.getInfo(pinWithState.getPiInstance().getLastRegisteredAddress())
								.getAvailablePins().spliterator(), false
					)
					.filter(p -> p.getBoardId().equals(pinWithState.getBoardId()))
					.findFirst()
					.orElseThrow(() -> new PiInterfaceServiceException("The pin is no longer available"));
			} else {
				// Usual scenario
				return ret;
			}
		}
		// If the button does not attempt to set a certain target device state
		else {
			// Just click the pin in a normal manner
			return clickPinAndUpdateState(pinWithState);
		}
	}

	private PIInstancePin clickPinAndUpdateState(PinWithState pin) {
		// Tell the PI to click the pin and get the response
		// If anything is wrong on the network or with the PI itself,
		// this will throw an exception, preventing the update of the state
		// in the local registry
		PIInstancePin ret = clientService.clickPinByBoardId(
			pin.getPiInstance().getLastRegisteredAddress(),
			pin.getBoardId(),
			pin.getDelayMs() == null ? 300 : pin.getDelayMs()
		);

		// Update the state in the local registry:
		//    - increment the state
		//    - if the state has reached the max number of states, then set it back to 0
		if (pinHasStates(pin)) {
			nextPinState(pin);
		}

		// Finally, return the response from the PI
		return ret;
	}

	private static boolean pinHasStates(PinWithState pin) {
		return pin.getStatesCount() != null && pin.getStatesCount() > 0;
	}

	private static void nextPinState(PinWithState pin) {
		pin.setCurrentState(pin.getCurrentState() + 1);
		if (pin.getCurrentState() >= pin.getStatesCount()) {
			pin.setCurrentState(0L);
		}
	}

	public void setPinButtonState(Pin pin, int buttonState) {
		if (pin == null) {
			return;
		}

		PinWithState pinWithState = resolvePinWithState(pin);

		clientService.switchPinByBoardId(
				pin.getPiInstance().getLastRegisteredAddress(),
				pin.getBoardId(),
				buttonState
			);

		if (buttonState == 0) {
			if (pinHasStates(pinWithState)) {
				nextPinState(pinWithState);
			}
		}
	}

	private PIInstancePin switchPin(Pin pin, int buttonState) {
		return clientService.switchPinByBoardId(
				pin.getPiInstance().getLastRegisteredAddress(),
				pin.getBoardId(),
				buttonState == 0 ? 1 : 0
			);
	}

	private PinWithState resolvePinWithState(Pin pin) {
		PinWithState ret = pinsRepository.get(pin.getId());

		if (ret == null) {
			ret = new PinWithState(pin);
			pinsRepository.put(pin.getId(), ret);
		}

		return ret;
	}

	/**
	 * Buttons which are newly added to the may not have had the chance to be added to the
	 * repository. If this is the case, then they are sure to be added by this method, along
	 * with the initial status 0.
	 */
	private UiButtonWithState resolveButton(Long buttonId) {
		UiButtonWithState button = uiButtonsRepository.get(buttonId);

		if (button == null) {
			UiButton dbButton = dataService.getUiButtonsRepository().findById(buttonId)
					.orElseThrow(() -> new IllegalArgumentException("A button with the given id is not registered"));

			button = new UiButtonWithState(dbButton, 0);
			uiButtonsRepository.put(dbButton.getId(), button);
		}

		return button;
	}

	/**
	 * The state (i.e. current order) of pin groups is held in memory, just like that of UI buttons.
	 * Just like resolveButton, this method retrieves the state of the group with the given id. If
	 * there is no state registered in memory, then an initial state is registered for the group.
	 */
	private PinGroupWithState resolvePinGroup(Long groupId) {
		PinGroupWithState group = pinGroupsRepository.get(groupId);

		if (group == null) {
			PinGroup dbGroup = dataService.getPinGroupsRepository().findById(groupId)
					.orElseThrow(() -> new IllegalArgumentException("A group with the given id is not reistered"));

			group = new PinGroupWithState(dbGroup);
			pinGroupsRepository.put(dbGroup.getId(), group);
		}

		return group;
	}

	@Transactional
	public UiButtonWithState saveUiButton(UiButton newButton) {
		UiButton savedButton = dataService.getUiButtonsRepository().save(newButton);
		UiButtonWithState registeredButton = new UiButtonWithState(savedButton, 0);
		uiButtonsRepository.put(savedButton.getId(), registeredButton);
		return registeredButton;
	}

	@Transactional
	public void deleteUiButton(Long uiButtonId) {
		dataService.getUiButtonsRepository().deleteById(uiButtonId);
		uiButtonsRepository.remove(uiButtonId);
	}

	@Transactional
	public Pin savePin(Pin pin) {
		// Save the pin
		Pin savedPin = dataService.getPinsRepository().save(pin);

		// Update any buttons referencing the pin directly
		for (UiButtonWithState btn : uiButtonsRepository.values()) {
			if (btn.getLinkedToPin() != null) {
				if (btn.getLinkedToPin().getId().equals(savedPin.getId())) {
					btn.setLinkedToPin(savedPin);
				}
			}
		}
		
		// Return the saved pin
		return savedPin;
	}

	public Iterable<UiButtonWithState> bulkSaveUiButtons(Iterable<UiButton> uiButtons) { 
		Iterable<UiButton> savedUiButtons
			= dataService.getUiButtonsRepository().saveAll(uiButtons);

		savedUiButtons.forEach(savedButton -> {
			UiButtonWithState registeredButton = new UiButtonWithState(savedButton, 0);
			uiButtonsRepository.put(savedButton.getId(), registeredButton);
		});
		
		return uiButtonsRepository.values();
	}

	public Iterable<UiButtonWithState> getUiButtonsOnPanel(Long panelId) {
		Collection<UiButtonWithState> buttons = uiButtonsRepository.values();

		if (buttons == null) {
			return null;
		}

		return buttons.stream()
				.filter(btn -> btn.getButtonsPanel() != null && btn.getButtonsPanel().getId() == panelId)
				.collect(toList());
	}

	public void deleteUiButtonsPanel(Long panelId) {
		// Get the IDs of the buttons belonging to the panel with the given id,
		// as registered in the state repository
		List<Long> btnIds = uiButtonsRepository.values().stream()
				.filter(btn -> btn.getButtonsPanel().getId().equals(panelId))
				.map(btn -> btn.getId())
				.collect(toList());

		// Bulk delete the buttons from the database
		dataService.getUiButtonsRepository().bulkDelete(btnIds);

		// Remove the panel from the database
		dataService.getUiButtonsPanelsRepository().deleteById(panelId);

		// Remove the buttons from state repository
		btnIds.forEach(btnId -> {
			uiButtonsRepository.remove(btnId);			
		});
		
	}
}
