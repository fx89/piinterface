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

import com.desolatetimelines.piinterface.model.PinGroup;
import com.desolatetimelines.piinterface.rest.exception.PiInterfaceRESTException;
import com.desolatetimelines.piinterface.service.PiInterfaceService;

@RestController
@RequestMapping(value = "/api/pinGroups")
public class PinGroupsRestEndpoint {
	@Autowired
	private PiInterfaceService piInterface;

	@GetMapping(value = "/count")
	public Long count() {
		return piInterface.getDataService().getPinGroupsRepository().count();
	}

	@GetMapping(value = "/findOneById")
	public PinGroup findOneById(@RequestParam(name = "id") Long id) {
		return piInterface.getDataService().getPinGroupsRepository().findById(id).orElseThrow(
				() -> new PiInterfaceRESTException("Could not find pin group with id = [" + id + "]"));
	}

	@GetMapping(value = "/findAll")
	public Iterable<PinGroup> findAll() {
		return piInterface.getDataService().getPinGroupsRepository().findAll();
	}

	@GetMapping(value = "/pageAll")
	public Iterable<PinGroup> pageAll(@RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinGroupsRepository().findAll(PageRequest.of(pageNumber, pageSize)).getContent();
	}

	@PostMapping(value = "/save")
	public PinGroup save(@RequestBody PinGroup item) {
		return piInterface.getDataService().getPinGroupsRepository().save(item);
	}

	@Transactional
	@DeleteMapping(value = "/delete")
	public void delete(@RequestParam(name = "id") Long id) {
		piInterface.getDataService().getPinGroupsRepository().deleteById(id);
	}

	@Transactional
	@DeleteMapping(value = "/bulkDelete")
	public void bulkDelete(@RequestBody List<Long> ids) {
		piInterface.getDataService().getPinGroupsRepository().bulkDelete(ids);
	}

	@GetMapping(value = "/findOneByName")
	public PinGroup findOneByName(@RequestParam(name = "name") String name) {
		return piInterface.getDataService().getPinGroupsRepository().findOneByName(name).orElseThrow(
				() -> new PiInterfaceRESTException("Could not find pin group with name = [" + name + "]"));
	}

	@GetMapping(value = "/findAllByNameStartingWith")
	public List<PinGroup> findAllByNameStartingWith(@RequestParam(name = "name") String name) {
		return piInterface.getDataService().getPinGroupsRepository().findAllByNameStartingWith(name);
	}
	
	@GetMapping(value = "/findFirstByNameStartingWith")
	public PinGroup findFirstByNameStartingWith(@RequestParam(name = "name") String name) {
		return piInterface.getDataService().getPinGroupsRepository().findFirstByNameStartingWith(name);
	}

	@GetMapping(value = "/pageAllByTypeId")
	public List<PinGroup> pageAllByTypeId(@RequestParam(name = "typeId") Long typeId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinGroupsRepository().findAllByTypeId(typeId, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByTypeId")
	public List<PinGroup> findAllByTypeId(@RequestParam(name = "typeId") Long typeId) {
		return piInterface.getDataService().getPinGroupsRepository().findAllByTypeId(typeId);
	}
	
	@GetMapping(value = "/pageAllByTypeIdIn")
	public List<PinGroup> pageAllByTypeIdIn(@RequestBody() List<Long> typeIds, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinGroupsRepository().findAllByTypeIdIn(typeIds, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByTypeIdIn")
	public List<PinGroup> findAllByTypeIdIn(@RequestBody() List<Long> typeIds) {
		return piInterface.getDataService().getPinGroupsRepository().findAllByTypeIdIn(typeIds);
	}

	
	@GetMapping(value = "/findAllByTypeIdAndName")
	public List<PinGroup> findAllByTypeIdAndName(@RequestParam(name = "typeId") Long typeId, @RequestParam(name = "name") String name) {
		return piInterface.getDataService().getPinGroupsRepository().findAllByTypeIdAndName(typeId, name);
	}
	
	@GetMapping(value = "/findFirstByTypeIdAndName")
	public PinGroup findFirstByTypeIdAndName(@RequestParam(name = "typeId") Long typeId, @RequestParam(name = "name") String name) {
		return piInterface.getDataService().getPinGroupsRepository().findFirstByTypeIdAndName(typeId, name);
	}

	@GetMapping(value = "/findAllByTypeName")
	public List<PinGroup> findAllByTypeName(@RequestParam(name="typeName") String name) {
		return piInterface.getDataService().getPinGroupsRepository().findAllByTypeName(name);
	}
	
	@GetMapping(value = "/pageAllByTypeName")
	public List<PinGroup> pageAllByTypeName(@RequestParam(name="typeName") String name, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinGroupsRepository().findAllByTypeName(name, PageRequest.of(pageNumber, pageSize));
	}

}
