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

import com.desolatetimelines.piinterface.model.PinCalendarEntry;
import com.desolatetimelines.piinterface.rest.exception.PiInterfaceRESTException;
import com.desolatetimelines.piinterface.service.PiInterfaceService;

@RestController
@RequestMapping(value = "/api/pinCalendarEntries")
public class PinCalendarEntriesRestEndpoint {
	@Autowired
	private PiInterfaceService piInterface;

	@GetMapping(value = "/count")
	public Long count() {
		return piInterface.getDataService().getPinCalendarEntriesRepository().count();
	}

	@GetMapping(value = "/findOneById")
	public PinCalendarEntry findOneById(@RequestParam(name = "id") Long id) {
		return piInterface.getDataService().getPinCalendarEntriesRepository().findById(id).orElseThrow(
				() -> new PiInterfaceRESTException("Could not find pin calendar entry with id = [" + id + "]"));
	}

	@GetMapping(value = "/findAll")
	public Iterable<PinCalendarEntry> findAll() {
		return piInterface.getDataService().getPinCalendarEntriesRepository().findAll();
	}

	@PostMapping(value = "/save")
	public PinCalendarEntry save(@RequestBody PinCalendarEntry item) {
		return piInterface.getDataService().getPinCalendarEntriesRepository().save(item);
	}

	@Transactional
	@DeleteMapping(value = "/delete")
	public void delete(@RequestParam(name = "id") Long id) {
		piInterface.getDataService().getPinCalendarEntriesRepository().deleteById(id);
	}

	@Transactional
	@DeleteMapping(value = "/bulkDelete")
	public void bulkDelete(@RequestBody List<Long> ids) {
		piInterface.getDataService().getPinCalendarEntriesRepository().bulkDelete(ids);
	}
}
