package com.desolatetimelines.piinterface.rest.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desolatetimelines.piinterface.model.PinCalendarAction;
import com.desolatetimelines.piinterface.rest.exception.PiInterfaceRESTException;
import com.desolatetimelines.piinterface.service.PiInterfaceService;

@RestController
@RequestMapping(value = "/api/pinCalendarActions")
public class PinCalendarActionsRestEndpoint {
	@Autowired
	private PiInterfaceService piInterface;

	@GetMapping(value = "/count")
	public Long count() {
		return piInterface.getDataService().getPinCalendarActionsRepository().count();
	}

	@GetMapping(value = "/findOneById")
	public PinCalendarAction findOneById(@RequestParam(name = "id") Long id) {
		return piInterface.getDataService().getPinCalendarActionsRepository().findById(id).orElseThrow(
				() -> new PiInterfaceRESTException("Could not find pin calendar action with id = [" + id + "]"));
	}

	@GetMapping(value = "/findAll")
	public Iterable<PinCalendarAction> findAll() {
		return piInterface.getDataService().getPinCalendarActionsRepository().findAll();
	}

	@PostMapping(value = "/save")
	public PinCalendarAction save(@RequestBody PinCalendarAction item) {
		return piInterface.getDataService().getPinCalendarActionsRepository().save(item);
	}

	@Transactional
	@DeleteMapping(value = "/delete")
	public void delete(@RequestParam(name = "id") Long id) {
		piInterface.getDataService().getPinCalendarActionsRepository().deleteById(id);
	}

	@Transactional
	@DeleteMapping(value = "/bulkDelete")
	public void bulkDelete(@RequestBody List<Long> ids) {
		piInterface.getDataService().getPinCalendarActionsRepository().bulkDelete(ids);
	}
}
