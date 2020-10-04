package com.desolatetimelines.piinterface.rest.functionality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desolatetimelines.piinterface.service.PiInterfaceService;
import com.desolatetimelines.piinterface.service.model.Notification;

@RestController
@RequestMapping(value = "/api/notifications")
public class NotificationsFunctionalRestEndpoint {
	@Autowired
	private PiInterfaceService piInterface;

	@GetMapping(value = "/findAll")
	public Iterable<Notification> findAll() {
		return piInterface.getNotificationService().peekEntireQueue();
	}

	@GetMapping(value = "/findOne")
	public Notification findOne() {
		return piInterface.getNotificationService().peekQueue();
	}

	@GetMapping(value = "/findMany")
	public Iterable<Notification> findMany(@RequestParam() int howMany) {
		return piInterface.getNotificationService().peekQueue(howMany);
	}

	@GetMapping(value = "/findLast")
	public Notification findLast() {
		return piInterface.getNotificationService().peekLast();
	}

	@GetMapping(value = "/findLastX")
	public Iterable<Notification> findLastX(@RequestParam() int x) {
		return piInterface.getNotificationService().peekLast(x);
	}
}
