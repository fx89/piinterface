package com.desolatetimelines.piinterface.service;

import static com.desolatetimelines.piinterface.service.Constants.NOTIFICATION_TYPE_ERROR;
import static com.desolatetimelines.piinterface.service.Constants.NOTIFICATION_TYPE_INFO;
import static com.desolatetimelines.piinterface.service.Constants.NOTIFICATION_TYPE_WARNING;
import static com.desolatetimelines.piinterface.service.utils.IpAddressRangesUtil.getIpAddessRangeType;
import static com.desolatetimelines.piinterface.service.utils.IpAddressRangesUtil.parseIpAddressDigit;
import static com.desolatetimelines.piinterface.service.utils.IpAddressRangesUtil.toIpAddressDigit;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desolatetimelines.piinterface.model.IpAddressRange;
import com.desolatetimelines.piinterface.model.PiInstance;
import com.desolatetimelines.piinterface.model.Pin;
import com.desolatetimelines.piinterface.piclient.PiClientService;
import com.desolatetimelines.piinterface.piclient.model.PIInstance;
import com.desolatetimelines.piinterface.piclient.model.PIInstancePin;
import com.desolatetimelines.piinterface.service.exception.PiInterfaceServiceException;
import com.desolatetimelines.piinterface.service.model.Notification;
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
		
		// Inform end-user that the process has ended
		queueInfo("Processing", "Beginning to query the defined address ranges");
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
}
