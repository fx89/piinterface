package com.desolatetimelines.piinterface.rest.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desolatetimelines.piinterface.model.PinGroupPin;
import com.desolatetimelines.piinterface.rest.exception.PiInterfaceRESTException;
import com.desolatetimelines.piinterface.service.PiInterfaceService;

@RestController
@RequestMapping(value = "/api/pinGroupPins")
public class PinGroupPinsRestEndpoint {
	@Autowired
	private PiInterfaceService piInterface;

	@GetMapping(value = "/count")
	public Long count() {
		return piInterface.getDataService().getPinGroupPinsRepository().count();
	}

	@GetMapping(value = "/findOneById")
	public PinGroupPin findOneById(@RequestParam(name = "id") Long id) {
		return piInterface.getDataService().getPinGroupPinsRepository().findById(id).orElseThrow(
				() -> new PiInterfaceRESTException("Could not find pin group pin with id = [" + id + "]"));
	}

	@GetMapping(value = "/findAll")
	public Iterable<PinGroupPin> findAll() {
		return piInterface.getDataService().getPinGroupPinsRepository().findAll();
	}

	@GetMapping(value = "/pageAll")
	public Iterable<PinGroupPin> pageAll(@RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAll(PageRequest.of(pageNumber, pageSize)).getContent();
	}

	@PostMapping(value = "/save")
	public PinGroupPin save(@RequestBody PinGroupPin item) {
		return piInterface.getDataService().getPinGroupPinsRepository().save(item);
	}

	@Transactional
	@DeleteMapping(value = "/delete")
	public void delete(@RequestParam(name = "id") Long id) {
		piInterface.getDataService().getPinGroupPinsRepository().deleteById(id);
	}

	@Transactional
	@DeleteMapping(value = "/bulkDelete")
	public void bulkDelete(@RequestBody List<Long> ids) {
		piInterface.getDataService().getPinGroupPinsRepository().bulkDelete(ids);
	}

	@GetMapping(value = "/pageAllByPinGroupId")
	public List<PinGroupPin> pageAllByPinGroupId(@RequestParam(name = "pinGroupId") Long pinGroupId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinGroupId(pinGroupId, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByPinGroupId")
	public List<PinGroupPin> findAllByPinGroupId(@RequestParam(name = "pinGroupId") Long pinGroupId) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinGroupId(pinGroupId);
	}
	
	@GetMapping(value = "/pageAllByPinGroupIdIn")
	public List<PinGroupPin> pageAllByPinGroupIdIn(@RequestBody() List<Long> pinGroupIds, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinGroupIdIn(pinGroupIds, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByPinGroupIdIn")
	public List<PinGroupPin> findAllByPinGroupIdIn(@RequestBody() List<Long> pinGroupIds) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinGroupIdIn(pinGroupIds);
	}

	@GetMapping(value = "/pageAllByPinGroupIdAndPinId")
	public List<PinGroupPin> pageAllByPinGroupIdAndPinId(@RequestParam(name = "pinGroupId") Long pinGroupId, @RequestParam(name = "pinId") Long pinId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinGroupIdAndPinId(pinGroupId, pinId, PageRequest.of(pageNumber, pageSize));
	}
	
	@GetMapping(value = "/findAllByPinGroupIdAndPinId")
	public List<PinGroupPin> findAllByPinGroupIdAndPinId(@RequestParam(name = "pinGroupId") Long pinGroupId, @RequestParam(name = "pinId") Long pinId) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinGroupIdAndPinId(pinGroupId, pinId);
	}
	
	@GetMapping(value = "/findFirstByPinGroupIdAndPinId")
	public PinGroupPin findFirstByPinGroupIdAndPinId(@RequestParam(name = "pinGroupId") Long pinGroupId, @RequestParam(name = "pinId") Long pinId) {
		return piInterface.getDataService().getPinGroupPinsRepository().findFirstByPinGroupIdAndPinId(pinGroupId, pinId);
	}

	@GetMapping(value = "/findAllByPinGroupIdAndPinIdAndOrder")
	List<PinGroupPin> findAllByPinGroupIdAndPinIdAndOrder(@RequestParam(name = "pinGroupId") Long pinGroupId, @RequestParam(name = "pinId") Long pinId, @RequestParam(name = "order") Integer order) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinGroupIdAndPinIdAndOrder(pinGroupId, pinId, order);
	}
	
	@GetMapping(value = "/findAllByPinGroupIdAndOrder")
	public List<PinGroupPin> findAllByPinGroupIdAndOrder(@RequestParam(name = "pinGroupId") Long pinGroupId, @RequestParam(name = "order") Integer order) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinGroupIdAndOrder(pinGroupId, order);
	}
	
	@GetMapping(value = "/findFirstByPinGroupIdAndOrder")
	public PinGroupPin findFirstByPinGroupIdAndOrder(@RequestParam(name = "pinGroupId") Long pinGroupId, @RequestParam(name = "order") Integer order) {
		return piInterface.getDataService().getPinGroupPinsRepository().findFirstByPinGroupIdAndOrder(pinGroupId, order);
	}

	@GetMapping(value = "/findAllByPinGroupName")
	public List<PinGroupPin> findAllByPinGroupName(@RequestParam(name="pinGroupName") String name) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinGroupName(name);
	}
	
	@GetMapping(value = "/pageAllByPinGroupName")
	public List<PinGroupPin> pageAllByPinGroupName(@RequestParam(name="pinGroupName") String name, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinGroupName(name, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/pageAllByPinId")
	public List<PinGroupPin> pageAllByPinId(@RequestParam(name = "pinId") Long pinId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinId(pinId, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByPinId")
	public List<PinGroupPin> findAllByPinId(@RequestParam(name = "pinId") Long pinId) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinId(pinId);
	}
	
	@GetMapping(value = "/pageAllByPinIdIn")
	public List<PinGroupPin> pageAllByPinIdIn(@RequestBody() List<Long> pinIds, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinIdIn(pinIds, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByPinIdIn")
	public List<PinGroupPin> findAllByPinIdIn(@RequestBody() List<Long> pinIds) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinIdIn(pinIds);
	}

	@GetMapping(value = "/pageAllByPinIdAndPinGroupId")
	public List<PinGroupPin> pageAllByPinIdAndPinGroupId(@RequestParam(name = "pinId") Long pinId, @RequestParam(name = "pinGroupId") Long pinGroupId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinIdAndPinGroupId(pinId, pinGroupId, PageRequest.of(pageNumber, pageSize));
	}
	
	@GetMapping(value = "/findAllByPinIdAndPinGroupId")
	public List<PinGroupPin> findAllByPinIdAndPinGroupId(@RequestParam(name = "pinId") Long pinId, @RequestParam(name = "pinGroupId") Long pinGroupId) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinIdAndPinGroupId(pinId, pinGroupId);
	}
	
	@GetMapping(value = "/findFirstByPinIdAndPinGroupId")
	public PinGroupPin findFirstByPinIdAndPinGroupId(@RequestParam(name = "pinId") Long pinId, @RequestParam(name = "pinGroupId") Long pinGroupId) {
		return piInterface.getDataService().getPinGroupPinsRepository().findFirstByPinIdAndPinGroupId(pinId, pinGroupId);
	}

	@GetMapping(value = "/findAllByPinIdAndPinGroupIdAndOrder")
	List<PinGroupPin> findAllByPinIdAndPinGroupIdAndOrder(@RequestParam(name = "pinId") Long pinId, @RequestParam(name = "pinGroupId") Long pinGroupId, @RequestParam(name = "order") Integer order) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinIdAndPinGroupIdAndOrder(pinId, pinGroupId, order);
	}
	
	@GetMapping(value = "/findAllByPinIdAndOrder")
	public List<PinGroupPin> findAllByPinIdAndOrder(@RequestParam(name = "pinId") Long pinId, @RequestParam(name = "order") Integer order) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinIdAndOrder(pinId, order);
	}
	
	@GetMapping(value = "/findFirstByPinIdAndOrder")
	public PinGroupPin findFirstByPinIdAndOrder(@RequestParam(name = "pinId") Long pinId, @RequestParam(name = "order") Integer order) {
		return piInterface.getDataService().getPinGroupPinsRepository().findFirstByPinIdAndOrder(pinId, order);
	}

	@GetMapping(value = "/findAllByPinName")
	public List<PinGroupPin> findAllByPinName(@RequestParam(name="pinName") String name) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinName(name);
	}
	
	@GetMapping(value = "/pageAllByPinName")
	public List<PinGroupPin> pageAllByPinName(@RequestParam(name="pinName") String name, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinName(name, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByPinBoardId")
	public List<PinGroupPin> findAllByPinBoardId(@RequestParam(name="pinBoardId") Long boardId) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinBoardId(boardId);
	}
	
	@GetMapping(value = "/pageAllByPinBoardId")
	public List<PinGroupPin> pageAllByPinBoardId(@RequestParam(name="pinBoardId") Long boardId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinBoardId(boardId, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByPinGpioId")
	public List<PinGroupPin> findAllByPinGpioId(@RequestParam(name="pinGpioId") Long gpioId) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinGpioId(gpioId);
	}
	
	@GetMapping(value = "/pageAllByPinGpioId")
	public List<PinGroupPin> pageAllByPinGpioId(@RequestParam(name="pinGpioId") Long gpioId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinGpioId(gpioId, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByPinDelayMs")
	public List<PinGroupPin> findAllByPinDelayMs(@RequestParam(name="pinDelayMs") Integer delayMs) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinDelayMs(delayMs);
	}
	
	@GetMapping(value = "/pageAllByPinDelayMs")
	public List<PinGroupPin> pageAllByPinDelayMs(@RequestParam(name="pinDelayMs") Integer delayMs, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinDelayMs(delayMs, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByPinIsAvailable")
	public List<PinGroupPin> findAllByPinIsAvailable(@RequestParam(name="pinIsAvailable") Boolean isAvailable) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinIsAvailable(isAvailable);
	}
	
	@GetMapping(value = "/pageAllByPinIsAvailable")
	public List<PinGroupPin> pageAllByPinIsAvailable(@RequestParam(name="pinIsAvailable") Boolean isAvailable, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByPinIsAvailable(isAvailable, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/pageAllByOrder")
	public List<PinGroupPin> pageAllByOrder(@RequestParam(name = "order") Integer order, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByOrder(order, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByOrder")
	public List<PinGroupPin> findAllByOrder(@RequestParam(name = "order") Integer order) {
		return piInterface.getDataService().getPinGroupPinsRepository().findAllByOrder(order);
	}
}
