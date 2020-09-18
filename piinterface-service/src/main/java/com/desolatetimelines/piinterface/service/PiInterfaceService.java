package com.desolatetimelines.piinterface.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
public class PiInterfaceService {

	@Autowired
	@Getter
	private PiInterfaceDataService dataService;
}
