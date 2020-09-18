package com.desolatetimelines.piinterface.rest;

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

	@PostMapping(value = "/save")
	public Pin save(@RequestBody Pin item) {
		return piInterface.getDataService().getPinsRepository().save(item);
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

}
