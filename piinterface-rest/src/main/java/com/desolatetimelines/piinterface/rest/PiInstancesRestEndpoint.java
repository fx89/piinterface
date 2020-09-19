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

import com.desolatetimelines.piinterface.model.PiInstance;
import com.desolatetimelines.piinterface.rest.exception.PiInterfaceRESTException;
import com.desolatetimelines.piinterface.service.PiInterfaceService;

@RestController
@RequestMapping(value = "/api/piInstances")
public class PiInstancesRestEndpoint {
	@Autowired
	private PiInterfaceService piInterface;

	@GetMapping(value = "/count")
	public Long count() {
		return piInterface.getDataService().getPiInstancesRepository().count();
	}

	@GetMapping(value = "/findOneById")
	public PiInstance findOneById(@RequestParam(name = "id") Long id) {
		return piInterface.getDataService().getPiInstancesRepository().findById(id).orElseThrow(
				() -> new PiInterfaceRESTException("Could not find pi instance with id = [" + id + "]"));
	}

	@GetMapping(value = "/findAll")
	public Iterable<PiInstance> findAll() {
		return piInterface.getDataService().getPiInstancesRepository().findAll();
	}

	@GetMapping(value = "/pageAll")
	public Iterable<PiInstance> pageAll(@RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPiInstancesRepository().findAll(PageRequest.of(pageNumber, pageSize)).getContent();
	}

	@PostMapping(value = "/save")
	public PiInstance save(@RequestBody PiInstance item) {
		return piInterface.getDataService().getPiInstancesRepository().save(item);
	}

	@Transactional
	@DeleteMapping(value = "/delete")
	public void delete(@RequestParam(name = "id") Long id) {
		piInterface.getDataService().getPiInstancesRepository().deleteById(id);
	}

	@Transactional
	@DeleteMapping(value = "/bulkDelete")
	public void bulkDelete(@RequestBody List<Long> ids) {
		piInterface.getDataService().getPiInstancesRepository().bulkDelete(ids);
	}

	@GetMapping(value = "/findOneByName")
	public PiInstance findOneByName(@RequestParam(name = "name") String name) {
		return piInterface.getDataService().getPiInstancesRepository().findOneByName(name).orElseThrow(
				() -> new PiInterfaceRESTException("Could not find pi instance with name = [" + name + "]"));
	}

	@GetMapping(value = "/findAllByNameStartingWith")
	public List<PiInstance> findAllByNameStartingWith(@RequestParam(name = "name") String name) {
		return piInterface.getDataService().getPiInstancesRepository().findAllByNameStartingWith(name);
	}
	
	@GetMapping(value = "/findFirstByNameStartingWith")
	public PiInstance findFirstByNameStartingWith(@RequestParam(name = "name") String name) {
		return piInterface.getDataService().getPiInstancesRepository().findFirstByNameStartingWith(name);
	}

	@GetMapping(value = "/pageAllByLastRegisteredAddress")
	public List<PiInstance> pageAllByLastRegisteredAddress(@RequestParam(name = "lastRegisteredAddress") String lastRegisteredAddress, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPiInstancesRepository().findAllByLastRegisteredAddress(lastRegisteredAddress, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByLastRegisteredAddress")
	public List<PiInstance> findAllByLastRegisteredAddress(@RequestParam(name = "lastRegisteredAddress") String lastRegisteredAddress) {
		return piInterface.getDataService().getPiInstancesRepository().findAllByLastRegisteredAddress(lastRegisteredAddress);
	}

}
