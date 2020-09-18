package com.desolatetimelines.piinterface.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desolatetimelines.piinterface.dao.PinGroupPinsRepository;
import com.desolatetimelines.piinterface.dao.PinGroupTypesRepository;
import com.desolatetimelines.piinterface.dao.PinGroupsRepository;
import com.desolatetimelines.piinterface.dao.PinOperatingModesRepository;
import com.desolatetimelines.piinterface.dao.PinsRepository;

import lombok.Getter;

@Getter
@Service
public class PiInterfaceDataService {
	@Autowired
	private PinGroupPinsRepository pinGroupPinsRepository;

	@Autowired
	private PinGroupTypesRepository pinGroupTypesRepository;

	@Autowired
	private PinGroupsRepository pinGroupsRepository;

	@Autowired
	private PinOperatingModesRepository pinOperatingModesRepository;

	@Autowired
	private PinsRepository pinsRepository;

}
