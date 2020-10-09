package com.desolatetimelines.piinterface.rest.functionality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desolatetimelines.piinterface.model.Pin;
import com.desolatetimelines.piinterface.service.PiInterfaceService;

@RestController
@RequestMapping(value = "/api/pins")
public class PinsFunctionalRestEndpoint {

	@Autowired
	private PiInterfaceService piInterface;
	
	@PostMapping(value = "/save")
	public Pin save(@RequestBody Pin item) {
		return piInterface.savePin(item);
	}
}
