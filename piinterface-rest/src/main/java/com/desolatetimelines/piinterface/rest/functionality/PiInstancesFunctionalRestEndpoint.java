package com.desolatetimelines.piinterface.rest.functionality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desolatetimelines.piinterface.service.PiInterfaceService;

@RestController
@RequestMapping(value = "/api/piInstances")
public class PiInstancesFunctionalRestEndpoint {

	@Autowired
	private PiInterfaceService piInterface;

	@GetMapping(value = "/synchronizePiInstances")
	public void synchronizePiInstances() {
		piInterface.synchronizePiInstances();
	}

	@GetMapping(value = "/resyncPiInstance")
	public void resyncPiInstance(@RequestParam() Long piInstanceId) {
		piInterface.resyncPiInstance(piInstanceId);
	}
}
