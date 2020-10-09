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

import com.desolatetimelines.piinterface.model.Icon;
import com.desolatetimelines.piinterface.rest.exception.PiInterfaceRESTException;
import com.desolatetimelines.piinterface.service.PiInterfaceService;

@RestController
@RequestMapping(value = "/api/icons")
public class IconsRestEndpoint {
	@Autowired
	private PiInterfaceService piInterface;

	@GetMapping(value = "/count")
	public Long count() {
		return piInterface.getDataService().getIconsRepository().count();
	}

	@GetMapping(value = "/findOneById")
	public Icon findOneById(@RequestParam(name = "id") Long id) {
		return piInterface.getDataService().getIconsRepository().findById(id).orElseThrow(
				() -> new PiInterfaceRESTException("Could not find icon with id = [" + id + "]"));
	}

	@GetMapping(value = "/findAll")
	public Iterable<Icon> findAll() {
		return piInterface.getDataService().getIconsRepository().findAll();
	}

	@GetMapping(value = "/pageAll")
	public Iterable<Icon> pageAll(@RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getIconsRepository().findAll(PageRequest.of(pageNumber, pageSize)).getContent();
	}

	@PostMapping(value = "/save")
	public Icon save(@RequestBody Icon item) {
		return piInterface.getDataService().getIconsRepository().save(item);
	}

	@Transactional
	@DeleteMapping(value = "/delete")
	public void delete(@RequestParam(name = "id") Long id) {
		piInterface.getDataService().getIconsRepository().deleteById(id);
	}

	@Transactional
	@DeleteMapping(value = "/bulkDelete")
	public void bulkDelete(@RequestBody List<Long> ids) {
		piInterface.getDataService().getIconsRepository().bulkDelete(ids);
	}

	@GetMapping(value = "/findOneByName")
	public Icon findOneByName(@RequestParam(name = "name") String name) {
		return piInterface.getDataService().getIconsRepository().findOneByName(name).orElseThrow(
				() -> new PiInterfaceRESTException("Could not find icon with name = [" + name + "]"));
	}

	@GetMapping(value = "/findAllByNameStartingWith")
	public List<Icon> findAllByNameStartingWith(@RequestParam(name = "name") String name) {
		return piInterface.getDataService().getIconsRepository().findAllByNameStartingWith(name);
	}
	
	@GetMapping(value = "/findFirstByNameStartingWith")
	public Icon findFirstByNameStartingWith(@RequestParam(name = "name") String name) {
		return piInterface.getDataService().getIconsRepository().findFirstByNameStartingWith(name);
	}

}
