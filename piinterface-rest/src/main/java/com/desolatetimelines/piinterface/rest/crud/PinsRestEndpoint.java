package com.desolatetimelines.piinterface.rest.crud;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desolatetimelines.piinterface.model.Pin;
import com.desolatetimelines.piinterface.rest.exception.PiInterfaceRESTException;
import com.desolatetimelines.piinterface.service.PiInterfaceService;

@RestController
@RequestMapping(value = "/api/pins")
public class PinsRestEndpoint {
	@Autowired
	private PiInterfaceService piInterface;

	@GetMapping(value = "/count")
	public Long count() {
		return piInterface.getDataService().getPinsRepository().count();
	}

	@GetMapping(value = "/findOneById")
	public Pin findOneById(@RequestParam(name = "id") Long id) {
		return piInterface.getDataService().getPinsRepository().findById(id).orElseThrow(
				() -> new PiInterfaceRESTException("Could not find pin with id = [" + id + "]"));
	}

	@GetMapping(value = "/findAll")
	public Iterable<Pin> findAll() {
		return piInterface.getDataService().getPinsRepository().findAll();
	}

	@GetMapping(value = "/pageAll")
	public Iterable<Pin> pageAll(@RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinsRepository().findAll(PageRequest.of(pageNumber, pageSize)).getContent();
	}

	@Transactional
	@DeleteMapping(value = "/delete")
	public void delete(@RequestParam(name = "id") Long id) {
		piInterface.getDataService().getPinsRepository().deleteById(id);
	}

	@Transactional
	@DeleteMapping(value = "/bulkDelete")
	public void bulkDelete(@RequestBody List<Long> ids) {
		piInterface.getDataService().getPinsRepository().bulkDelete(ids);
	}

	@GetMapping(value = "/pageAllByPiInstanceId")
	public List<Pin> pageAllByPiInstanceId(@RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinsRepository().findAllByPiInstanceId(piInstanceId, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByPiInstanceId")
	public List<Pin> findAllByPiInstanceId(@RequestParam(name = "piInstanceId") Long piInstanceId) {
		return piInterface.getDataService().getPinsRepository().findAllByPiInstanceId(piInstanceId);
	}
	
	@GetMapping(value = "/pageAllByPiInstanceIdIn")
	public List<Pin> pageAllByPiInstanceIdIn(@RequestBody() List<Long> piInstanceIds, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinsRepository().findAllByPiInstanceIdIn(piInstanceIds, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByPiInstanceIdIn")
	public List<Pin> findAllByPiInstanceIdIn(@RequestBody() List<Long> piInstanceIds) {
		return piInterface.getDataService().getPinsRepository().findAllByPiInstanceIdIn(piInstanceIds);
	}

	
	@GetMapping(value = "/findAllByPiInstanceIdAndName")
	public List<Pin> findAllByPiInstanceIdAndName(@RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "name") String name) {
		return piInterface.getDataService().getPinsRepository().findAllByPiInstanceIdAndName(piInstanceId, name);
	}
	
	@GetMapping(value = "/findFirstByPiInstanceIdAndName")
	public Pin findFirstByPiInstanceIdAndName(@RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "name") String name) {
		return piInterface.getDataService().getPinsRepository().findFirstByPiInstanceIdAndName(piInstanceId, name);
	}

	
	@GetMapping(value = "/findAllByPiInstanceIdAndBoardId")
	public List<Pin> findAllByPiInstanceIdAndBoardId(@RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "boardId") Long boardId) {
		return piInterface.getDataService().getPinsRepository().findAllByPiInstanceIdAndBoardId(piInstanceId, boardId);
	}
	
	@GetMapping(value = "/findFirstByPiInstanceIdAndBoardId")
	public Pin findFirstByPiInstanceIdAndBoardId(@RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "boardId") Long boardId) {
		return piInterface.getDataService().getPinsRepository().findFirstByPiInstanceIdAndBoardId(piInstanceId, boardId);
	}

	
	@GetMapping(value = "/findAllByPiInstanceIdAndGpioId")
	public List<Pin> findAllByPiInstanceIdAndGpioId(@RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "gpioId") Long gpioId) {
		return piInterface.getDataService().getPinsRepository().findAllByPiInstanceIdAndGpioId(piInstanceId, gpioId);
	}
	
	@GetMapping(value = "/findFirstByPiInstanceIdAndGpioId")
	public Pin findFirstByPiInstanceIdAndGpioId(@RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "gpioId") Long gpioId) {
		return piInterface.getDataService().getPinsRepository().findFirstByPiInstanceIdAndGpioId(piInstanceId, gpioId);
	}

	@GetMapping(value = "/pageAllByPiInstanceIdAndOperatingModeId")
	public List<Pin> pageAllByPiInstanceIdAndOperatingModeId(@RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinsRepository().findAllByPiInstanceIdAndOperatingModeId(piInstanceId, operatingModeId, PageRequest.of(pageNumber, pageSize));
	}
	
	@GetMapping(value = "/findAllByPiInstanceIdAndOperatingModeId")
	public List<Pin> findAllByPiInstanceIdAndOperatingModeId(@RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "operatingModeId") Long operatingModeId) {
		return piInterface.getDataService().getPinsRepository().findAllByPiInstanceIdAndOperatingModeId(piInstanceId, operatingModeId);
	}
	
	@GetMapping(value = "/findFirstByPiInstanceIdAndOperatingModeId")
	public Pin findFirstByPiInstanceIdAndOperatingModeId(@RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "operatingModeId") Long operatingModeId) {
		return piInterface.getDataService().getPinsRepository().findFirstByPiInstanceIdAndOperatingModeId(piInstanceId, operatingModeId);
	}

	@GetMapping(value = "/findAllByPiInstanceIdAndOperatingModeIdAndName")
	List<Pin> findAllByPiInstanceIdAndOperatingModeIdAndName(@RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "name") String name) {
		return piInterface.getDataService().getPinsRepository().findAllByPiInstanceIdAndOperatingModeIdAndName(piInstanceId, operatingModeId, name);
	}

	@GetMapping(value = "/findAllByPiInstanceIdAndOperatingModeIdAndBoardId")
	List<Pin> findAllByPiInstanceIdAndOperatingModeIdAndBoardId(@RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "boardId") Long boardId) {
		return piInterface.getDataService().getPinsRepository().findAllByPiInstanceIdAndOperatingModeIdAndBoardId(piInstanceId, operatingModeId, boardId);
	}

	@GetMapping(value = "/findAllByPiInstanceIdAndOperatingModeIdAndGpioId")
	List<Pin> findAllByPiInstanceIdAndOperatingModeIdAndGpioId(@RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "gpioId") Long gpioId) {
		return piInterface.getDataService().getPinsRepository().findAllByPiInstanceIdAndOperatingModeIdAndGpioId(piInstanceId, operatingModeId, gpioId);
	}

	@GetMapping(value = "/findAllByPiInstanceIdAndOperatingModeIdAndDelayMs")
	List<Pin> findAllByPiInstanceIdAndOperatingModeIdAndDelayMs(@RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "delayMs") Integer delayMs) {
		return piInterface.getDataService().getPinsRepository().findAllByPiInstanceIdAndOperatingModeIdAndDelayMs(piInstanceId, operatingModeId, delayMs);
	}

	@GetMapping(value = "/findAllByPiInstanceIdAndOperatingModeIdAndIsAvailable")
	List<Pin> findAllByPiInstanceIdAndOperatingModeIdAndIsAvailable(@RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "isAvailable") Boolean isAvailable) {
		return piInterface.getDataService().getPinsRepository().findAllByPiInstanceIdAndOperatingModeIdAndIsAvailable(piInstanceId, operatingModeId, isAvailable);
	}

	
	@GetMapping(value = "/findAllByPiInstanceIdAndDelayMs")
	public List<Pin> findAllByPiInstanceIdAndDelayMs(@RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "delayMs") Integer delayMs) {
		return piInterface.getDataService().getPinsRepository().findAllByPiInstanceIdAndDelayMs(piInstanceId, delayMs);
	}
	
	@GetMapping(value = "/findFirstByPiInstanceIdAndDelayMs")
	public Pin findFirstByPiInstanceIdAndDelayMs(@RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "delayMs") Integer delayMs) {
		return piInterface.getDataService().getPinsRepository().findFirstByPiInstanceIdAndDelayMs(piInstanceId, delayMs);
	}

	
	@GetMapping(value = "/findAllByPiInstanceIdAndIsAvailable")
	public List<Pin> findAllByPiInstanceIdAndIsAvailable(@RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "isAvailable") Boolean isAvailable) {
		return piInterface.getDataService().getPinsRepository().findAllByPiInstanceIdAndIsAvailable(piInstanceId, isAvailable);
	}
	
	@GetMapping(value = "/findFirstByPiInstanceIdAndIsAvailable")
	public Pin findFirstByPiInstanceIdAndIsAvailable(@RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "isAvailable") Boolean isAvailable) {
		return piInterface.getDataService().getPinsRepository().findFirstByPiInstanceIdAndIsAvailable(piInstanceId, isAvailable);
	}

	@GetMapping(value = "/findAllByPiInstanceName")
	public List<Pin> findAllByPiInstanceName(@RequestParam(name="piInstanceName") String name) {
		return piInterface.getDataService().getPinsRepository().findAllByPiInstanceName(name);
	}
	
	@GetMapping(value = "/pageAllByPiInstanceName")
	public List<Pin> pageAllByPiInstanceName(@RequestParam(name="piInstanceName") String name, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinsRepository().findAllByPiInstanceName(name, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByPiInstanceLastRegisteredAddress")
	public List<Pin> findAllByPiInstanceLastRegisteredAddress(@RequestParam(name="piInstanceLastRegisteredAddress") String lastRegisteredAddress) {
		return piInterface.getDataService().getPinsRepository().findAllByPiInstanceLastRegisteredAddress(lastRegisteredAddress);
	}
	
	@GetMapping(value = "/pageAllByPiInstanceLastRegisteredAddress")
	public List<Pin> pageAllByPiInstanceLastRegisteredAddress(@RequestParam(name="piInstanceLastRegisteredAddress") String lastRegisteredAddress, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinsRepository().findAllByPiInstanceLastRegisteredAddress(lastRegisteredAddress, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findOneByName")
	public Pin findOneByName(@RequestParam(name = "name") String name) {
		return piInterface.getDataService().getPinsRepository().findOneByName(name).orElseThrow(
				() -> new PiInterfaceRESTException("Could not find pin with name = [" + name + "]"));
	}

	@GetMapping(value = "/findAllByNameStartingWith")
	public List<Pin> findAllByNameStartingWith(@RequestParam(name = "name") String name) {
		return piInterface.getDataService().getPinsRepository().findAllByNameStartingWith(name);
	}
	
	@GetMapping(value = "/findFirstByNameStartingWith")
	public Pin findFirstByNameStartingWith(@RequestParam(name = "name") String name) {
		return piInterface.getDataService().getPinsRepository().findFirstByNameStartingWith(name);
	}

	@GetMapping(value = "/pageAllByBoardId")
	public List<Pin> pageAllByBoardId(@RequestParam(name = "boardId") Long boardId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinsRepository().findAllByBoardId(boardId, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByBoardId")
	public List<Pin> findAllByBoardId(@RequestParam(name = "boardId") Long boardId) {
		return piInterface.getDataService().getPinsRepository().findAllByBoardId(boardId);
	}

	@GetMapping(value = "/pageAllByGpioId")
	public List<Pin> pageAllByGpioId(@RequestParam(name = "gpioId") Long gpioId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinsRepository().findAllByGpioId(gpioId, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByGpioId")
	public List<Pin> findAllByGpioId(@RequestParam(name = "gpioId") Long gpioId) {
		return piInterface.getDataService().getPinsRepository().findAllByGpioId(gpioId);
	}

	@GetMapping(value = "/pageAllByOperatingModeId")
	public List<Pin> pageAllByOperatingModeId(@RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinsRepository().findAllByOperatingModeId(operatingModeId, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByOperatingModeId")
	public List<Pin> findAllByOperatingModeId(@RequestParam(name = "operatingModeId") Long operatingModeId) {
		return piInterface.getDataService().getPinsRepository().findAllByOperatingModeId(operatingModeId);
	}
	
	@GetMapping(value = "/pageAllByOperatingModeIdIn")
	public List<Pin> pageAllByOperatingModeIdIn(@RequestBody() List<Long> operatingModeIds, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinsRepository().findAllByOperatingModeIdIn(operatingModeIds, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByOperatingModeIdIn")
	public List<Pin> findAllByOperatingModeIdIn(@RequestBody() List<Long> operatingModeIds) {
		return piInterface.getDataService().getPinsRepository().findAllByOperatingModeIdIn(operatingModeIds);
	}

	@GetMapping(value = "/pageAllByOperatingModeIdAndPiInstanceId")
	public List<Pin> pageAllByOperatingModeIdAndPiInstanceId(@RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinsRepository().findAllByOperatingModeIdAndPiInstanceId(operatingModeId, piInstanceId, PageRequest.of(pageNumber, pageSize));
	}
	
	@GetMapping(value = "/findAllByOperatingModeIdAndPiInstanceId")
	public List<Pin> findAllByOperatingModeIdAndPiInstanceId(@RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "piInstanceId") Long piInstanceId) {
		return piInterface.getDataService().getPinsRepository().findAllByOperatingModeIdAndPiInstanceId(operatingModeId, piInstanceId);
	}
	
	@GetMapping(value = "/findFirstByOperatingModeIdAndPiInstanceId")
	public Pin findFirstByOperatingModeIdAndPiInstanceId(@RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "piInstanceId") Long piInstanceId) {
		return piInterface.getDataService().getPinsRepository().findFirstByOperatingModeIdAndPiInstanceId(operatingModeId, piInstanceId);
	}

	@GetMapping(value = "/findAllByOperatingModeIdAndPiInstanceIdAndName")
	List<Pin> findAllByOperatingModeIdAndPiInstanceIdAndName(@RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "name") String name) {
		return piInterface.getDataService().getPinsRepository().findAllByOperatingModeIdAndPiInstanceIdAndName(operatingModeId, piInstanceId, name);
	}

	@GetMapping(value = "/findAllByOperatingModeIdAndPiInstanceIdAndBoardId")
	List<Pin> findAllByOperatingModeIdAndPiInstanceIdAndBoardId(@RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "boardId") Long boardId) {
		return piInterface.getDataService().getPinsRepository().findAllByOperatingModeIdAndPiInstanceIdAndBoardId(operatingModeId, piInstanceId, boardId);
	}

	@GetMapping(value = "/findAllByOperatingModeIdAndPiInstanceIdAndGpioId")
	List<Pin> findAllByOperatingModeIdAndPiInstanceIdAndGpioId(@RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "gpioId") Long gpioId) {
		return piInterface.getDataService().getPinsRepository().findAllByOperatingModeIdAndPiInstanceIdAndGpioId(operatingModeId, piInstanceId, gpioId);
	}

	@GetMapping(value = "/findAllByOperatingModeIdAndPiInstanceIdAndDelayMs")
	List<Pin> findAllByOperatingModeIdAndPiInstanceIdAndDelayMs(@RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "delayMs") Integer delayMs) {
		return piInterface.getDataService().getPinsRepository().findAllByOperatingModeIdAndPiInstanceIdAndDelayMs(operatingModeId, piInstanceId, delayMs);
	}

	@GetMapping(value = "/findAllByOperatingModeIdAndPiInstanceIdAndIsAvailable")
	List<Pin> findAllByOperatingModeIdAndPiInstanceIdAndIsAvailable(@RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "piInstanceId") Long piInstanceId, @RequestParam(name = "isAvailable") Boolean isAvailable) {
		return piInterface.getDataService().getPinsRepository().findAllByOperatingModeIdAndPiInstanceIdAndIsAvailable(operatingModeId, piInstanceId, isAvailable);
	}

	
	@GetMapping(value = "/findAllByOperatingModeIdAndName")
	public List<Pin> findAllByOperatingModeIdAndName(@RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "name") String name) {
		return piInterface.getDataService().getPinsRepository().findAllByOperatingModeIdAndName(operatingModeId, name);
	}
	
	@GetMapping(value = "/findFirstByOperatingModeIdAndName")
	public Pin findFirstByOperatingModeIdAndName(@RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "name") String name) {
		return piInterface.getDataService().getPinsRepository().findFirstByOperatingModeIdAndName(operatingModeId, name);
	}

	
	@GetMapping(value = "/findAllByOperatingModeIdAndBoardId")
	public List<Pin> findAllByOperatingModeIdAndBoardId(@RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "boardId") Long boardId) {
		return piInterface.getDataService().getPinsRepository().findAllByOperatingModeIdAndBoardId(operatingModeId, boardId);
	}
	
	@GetMapping(value = "/findFirstByOperatingModeIdAndBoardId")
	public Pin findFirstByOperatingModeIdAndBoardId(@RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "boardId") Long boardId) {
		return piInterface.getDataService().getPinsRepository().findFirstByOperatingModeIdAndBoardId(operatingModeId, boardId);
	}

	
	@GetMapping(value = "/findAllByOperatingModeIdAndGpioId")
	public List<Pin> findAllByOperatingModeIdAndGpioId(@RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "gpioId") Long gpioId) {
		return piInterface.getDataService().getPinsRepository().findAllByOperatingModeIdAndGpioId(operatingModeId, gpioId);
	}
	
	@GetMapping(value = "/findFirstByOperatingModeIdAndGpioId")
	public Pin findFirstByOperatingModeIdAndGpioId(@RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "gpioId") Long gpioId) {
		return piInterface.getDataService().getPinsRepository().findFirstByOperatingModeIdAndGpioId(operatingModeId, gpioId);
	}

	
	@GetMapping(value = "/findAllByOperatingModeIdAndDelayMs")
	public List<Pin> findAllByOperatingModeIdAndDelayMs(@RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "delayMs") Integer delayMs) {
		return piInterface.getDataService().getPinsRepository().findAllByOperatingModeIdAndDelayMs(operatingModeId, delayMs);
	}
	
	@GetMapping(value = "/findFirstByOperatingModeIdAndDelayMs")
	public Pin findFirstByOperatingModeIdAndDelayMs(@RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "delayMs") Integer delayMs) {
		return piInterface.getDataService().getPinsRepository().findFirstByOperatingModeIdAndDelayMs(operatingModeId, delayMs);
	}

	
	@GetMapping(value = "/findAllByOperatingModeIdAndIsAvailable")
	public List<Pin> findAllByOperatingModeIdAndIsAvailable(@RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "isAvailable") Boolean isAvailable) {
		return piInterface.getDataService().getPinsRepository().findAllByOperatingModeIdAndIsAvailable(operatingModeId, isAvailable);
	}
	
	@GetMapping(value = "/findFirstByOperatingModeIdAndIsAvailable")
	public Pin findFirstByOperatingModeIdAndIsAvailable(@RequestParam(name = "operatingModeId") Long operatingModeId, @RequestParam(name = "isAvailable") Boolean isAvailable) {
		return piInterface.getDataService().getPinsRepository().findFirstByOperatingModeIdAndIsAvailable(operatingModeId, isAvailable);
	}

	@GetMapping(value = "/findAllByOperatingModeName")
	public List<Pin> findAllByOperatingModeName(@RequestParam(name="operatingModeName") String name) {
		return piInterface.getDataService().getPinsRepository().findAllByOperatingModeName(name);
	}
	
	@GetMapping(value = "/pageAllByOperatingModeName")
	public List<Pin> pageAllByOperatingModeName(@RequestParam(name="operatingModeName") String name, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinsRepository().findAllByOperatingModeName(name, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/pageAllByDelayMs")
	public List<Pin> pageAllByDelayMs(@RequestParam(name = "delayMs") Integer delayMs, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinsRepository().findAllByDelayMs(delayMs, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByDelayMs")
	public List<Pin> findAllByDelayMs(@RequestParam(name = "delayMs") Integer delayMs) {
		return piInterface.getDataService().getPinsRepository().findAllByDelayMs(delayMs);
	}

	@GetMapping(value = "/pageAllByIsAvailable")
	public List<Pin> pageAllByIsAvailable(@RequestParam(name = "isAvailable") Boolean isAvailable, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinsRepository().findAllByIsAvailable(isAvailable, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByIsAvailable")
	public List<Pin> findAllByIsAvailable(@RequestParam(name = "isAvailable") Boolean isAvailable) {
		return piInterface.getDataService().getPinsRepository().findAllByIsAvailable(isAvailable);
	}

	@GetMapping(value = "/findAllByPinGroupId")
	public List<Pin> findAllByIsAvailable(@RequestParam() Long pinGroupId) {
		return piInterface.getDataService().getPinGroupPinsRepository()
				.findAllByPinGroupId(pinGroupId)
				.stream()
				.map(item -> item.getPin())
				.collect(toList());
	}

}
