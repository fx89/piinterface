package com.desolatetimelines.piinterface.service.model;

import java.util.HashMap;
import java.util.Map;

import com.desolatetimelines.piinterface.model.PinGroup;

import lombok.Getter;
import lombok.Setter;

public class PinGroupWithState extends PinGroup {

	@Getter
	@Setter
	private int currentOrder;

	@Getter
	@Setter
	private Map<Long, Long> pinStates = new HashMap<>();

	public PinGroupWithState(PinGroup pinGroup) {
		this.setId(pinGroup.getId());
		this.setCurrentOrder(0);		
		this.setName(pinGroup.getName());
		this.setType(pinGroup.getType());
	}
}
