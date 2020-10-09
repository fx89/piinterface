package com.desolatetimelines.piinterface.rest.functionality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desolatetimelines.piinterface.model.PinGroupPin;
import com.desolatetimelines.piinterface.service.PiInterfaceService;

@RestController
@RequestMapping(value = "/api/pinGroupPins")
public class PinGroupPinsFunctionalRestEndpoint {
	@Autowired
	private PiInterfaceService piInterface;

	@GetMapping(value = "/mapPinOnGroup")
	public PinGroupPin mapPinOnGroup(@RequestParam() Long pinId, @RequestParam() Long groupId) {
		return piInterface.mapPinToPinGroup(pinId, groupId);
	}

	@GetMapping(value = "/moveUp")
	public PinGroupPin moveUp(@RequestParam() Long pinGroupPinId) {
		return piInterface.movePinUpInGroup(pinGroupPinId);
	}

	@GetMapping(value = "/moveDown")
	public PinGroupPin moveDown(@RequestParam() Long pinGroupPinId) {
		return piInterface.movePinDownInGroup(pinGroupPinId);
	}
}
