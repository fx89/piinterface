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

import com.desolatetimelines.piinterface.model.PinOperatingMode;
import com.desolatetimelines.piinterface.rest.exception.PiInterfaceRESTException;
import com.desolatetimelines.piinterface.service.PiInterfaceService;

@RestController
@RequestMapping(value = "/api/pinOperatingModes")
public class PinOperatingModesRestEndpoint {
	@Autowired
	private PiInterfaceService piInterface;

	@GetMapping(value = "/count")
	public Long count() {
		return piInterface.getDataService().getPinOperatingModesRepository().count();
	}

	@GetMapping(value = "/findOneById")
	public PinOperatingMode findOneById(@RequestParam(name = "id") Long id) {
		return piInterface.getDataService().getPinOperatingModesRepository().findById(id).orElseThrow(
				() -> new PiInterfaceRESTException("Could not find pin operating mode with id = [" + id + "]"));
	}

	@GetMapping(value = "/findAll")
	public Iterable<PinOperatingMode> findAll() {
		return piInterface.getDataService().getPinOperatingModesRepository().findAll();
	}

	@GetMapping(value = "/pageAll")
	public Iterable<PinOperatingMode> pageAll(@RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinOperatingModesRepository().findAll(PageRequest.of(pageNumber, pageSize)).getContent();
	}

	@PostMapping(value = "/save")
	public PinOperatingMode save(@RequestBody PinOperatingMode item) {
		return piInterface.getDataService().getPinOperatingModesRepository().save(item);
	}

	@Transactional
	@DeleteMapping(value = "/delete")
	public void delete(@RequestParam(name = "id") Long id) {
		piInterface.getDataService().getPinOperatingModesRepository().deleteById(id);
	}

	@Transactional
	@DeleteMapping(value = "/bulkDelete")
	public void bulkDelete(@RequestBody List<Long> ids) {
		piInterface.getDataService().getPinOperatingModesRepository().bulkDelete(ids);
	}

	@GetMapping(value = "/findOneByName")
	public PinOperatingMode findOneByName(@RequestParam(name = "name") String name) {
		return piInterface.getDataService().getPinOperatingModesRepository().findOneByName(name).orElseThrow(
				() -> new PiInterfaceRESTException("Could not find pin operating mode with name = [" + name + "]"));
	}

	@GetMapping(value = "/findAllByNameStartingWith")
	public List<PinOperatingMode> findAllByNameStartingWith(@RequestParam(name = "name") String name) {
		return piInterface.getDataService().getPinOperatingModesRepository().findAllByNameStartingWith(name);
	}
	
	@GetMapping(value = "/findFirstByNameStartingWith")
	public PinOperatingMode findFirstByNameStartingWith(@RequestParam(name = "name") String name) {
		return piInterface.getDataService().getPinOperatingModesRepository().findFirstByNameStartingWith(name);
	}

}
