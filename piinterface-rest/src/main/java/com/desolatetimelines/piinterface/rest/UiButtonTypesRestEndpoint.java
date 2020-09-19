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

import com.desolatetimelines.piinterface.model.UiButtonType;
import com.desolatetimelines.piinterface.rest.exception.PiInterfaceRESTException;
import com.desolatetimelines.piinterface.service.PiInterfaceService;

@RestController
@RequestMapping(value = "/api/uiButtonTypes")
public class UiButtonTypesRestEndpoint {
	@Autowired
	private PiInterfaceService piInterface;

	@GetMapping(value = "/count")
	public Long count() {
		return piInterface.getDataService().getUiButtonTypesRepository().count();
	}

	@GetMapping(value = "/findOneById")
	public UiButtonType findOneById(@RequestParam(name = "id") Long id) {
		return piInterface.getDataService().getUiButtonTypesRepository().findById(id).orElseThrow(
				() -> new PiInterfaceRESTException("Could not find ui button type with id = [" + id + "]"));
	}

	@GetMapping(value = "/findAll")
	public Iterable<UiButtonType> findAll() {
		return piInterface.getDataService().getUiButtonTypesRepository().findAll();
	}

	@GetMapping(value = "/pageAll")
	public Iterable<UiButtonType> pageAll(@RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonTypesRepository().findAll(PageRequest.of(pageNumber, pageSize)).getContent();
	}

	@PostMapping(value = "/save")
	public UiButtonType save(@RequestBody UiButtonType item) {
		return piInterface.getDataService().getUiButtonTypesRepository().save(item);
	}

	@Transactional
	@DeleteMapping(value = "/delete")
	public void delete(@RequestParam(name = "id") Long id) {
		piInterface.getDataService().getUiButtonTypesRepository().deleteById(id);
	}

	@Transactional
	@DeleteMapping(value = "/bulkDelete")
	public void bulkDelete(@RequestBody List<Long> ids) {
		piInterface.getDataService().getUiButtonTypesRepository().bulkDelete(ids);
	}

	@GetMapping(value = "/findOneByName")
	public UiButtonType findOneByName(@RequestParam(name = "name") String name) {
		return piInterface.getDataService().getUiButtonTypesRepository().findOneByName(name).orElseThrow(
				() -> new PiInterfaceRESTException("Could not find ui button type with name = [" + name + "]"));
	}

	@GetMapping(value = "/findAllByNameStartingWith")
	public List<UiButtonType> findAllByNameStartingWith(@RequestParam(name = "name") String name) {
		return piInterface.getDataService().getUiButtonTypesRepository().findAllByNameStartingWith(name);
	}
	
	@GetMapping(value = "/findFirstByNameStartingWith")
	public UiButtonType findFirstByNameStartingWith(@RequestParam(name = "name") String name) {
		return piInterface.getDataService().getUiButtonTypesRepository().findFirstByNameStartingWith(name);
	}

}
