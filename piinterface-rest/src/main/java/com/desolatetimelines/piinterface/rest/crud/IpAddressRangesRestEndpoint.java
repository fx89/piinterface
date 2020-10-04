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

import com.desolatetimelines.piinterface.model.IpAddressRange;
import com.desolatetimelines.piinterface.service.PiInterfaceService;

@RestController
@RequestMapping(value = "/api/ipAddressRanges")
public class IpAddressRangesRestEndpoint {
	@Autowired
	private PiInterfaceService piInterface;

	@GetMapping(value = "/findAll")
	public Iterable<IpAddressRange> findAll() {
		return piInterface.getDataService().getIpAddressRangesRepository().findAll();
	}

	@PostMapping(value = "/save")
	public IpAddressRange save(@RequestBody IpAddressRange item) {
		return piInterface.getDataService().getIpAddressRangesRepository().save(item);
	}

	@Transactional
	@DeleteMapping(value = "/delete")
	public void delete(@RequestParam(name = "id") Long id) {
		piInterface.getDataService().getIpAddressRangesRepository().deleteById(id);
	}

	@Transactional
	@DeleteMapping(value = "/bulkDelete")
	public void bulkDelete(@RequestBody List<Long> ids) {
		piInterface.getDataService().getIpAddressRangesRepository().bulkDelete(ids);
	}
}
