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

import com.desolatetimelines.piinterface.model.PinGroupType;
import com.desolatetimelines.piinterface.rest.exception.PiInterfaceRESTException;
import com.desolatetimelines.piinterface.service.PiInterfaceService;

@RestController
@RequestMapping(value = "/api/pinGroupTypes")
public class PinGroupTypesRestEndpoint {
	@Autowired
	private PiInterfaceService piInterface;

	@GetMapping(value = "/count")
	public Long count() {
		return piInterface.getDataService().getPinGroupTypesRepository().count();
	}

	@GetMapping(value = "/findOneById")
	public PinGroupType findOneById(@RequestParam(name = "id") Long id) {
		return piInterface.getDataService().getPinGroupTypesRepository().findById(id).orElseThrow(
				() -> new PiInterfaceRESTException("Could not find pin group type with id = [" + id + "]"));
	}

	@GetMapping(value = "/findAll")
	public Iterable<PinGroupType> findAll() {
		return piInterface.getDataService().getPinGroupTypesRepository().findAll();
	}

	@GetMapping(value = "/pageAll")
	public Iterable<PinGroupType> pageAll(@RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getPinGroupTypesRepository().findAll(PageRequest.of(pageNumber, pageSize)).getContent();
	}

	@PostMapping(value = "/save")
	public PinGroupType save(@RequestBody PinGroupType item) {
		return piInterface.getDataService().getPinGroupTypesRepository().save(item);
	}

	@Transactional
	@DeleteMapping(value = "/delete")
	public void delete(@RequestParam(name = "id") Long id) {
		piInterface.getDataService().getPinGroupTypesRepository().deleteById(id);
	}

	@Transactional
	@DeleteMapping(value = "/bulkDelete")
	public void bulkDelete(@RequestBody List<Long> ids) {
		piInterface.getDataService().getPinGroupTypesRepository().bulkDelete(ids);
	}

	@GetMapping(value = "/findOneByName")
	public PinGroupType findOneByName(@RequestParam(name = "name") String name) {
		return piInterface.getDataService().getPinGroupTypesRepository().findOneByName(name).orElseThrow(
				() -> new PiInterfaceRESTException("Could not find pin group type with name = [" + name + "]"));
	}

	@GetMapping(value = "/findAllByNameStartingWith")
	public List<PinGroupType> findAllByNameStartingWith(@RequestParam(name = "name") String name) {
		return piInterface.getDataService().getPinGroupTypesRepository().findAllByNameStartingWith(name);
	}
	
	@GetMapping(value = "/findFirstByNameStartingWith")
	public PinGroupType findFirstByNameStartingWith(@RequestParam(name = "name") String name) {
		return piInterface.getDataService().getPinGroupTypesRepository().findFirstByNameStartingWith(name);
	}

}
