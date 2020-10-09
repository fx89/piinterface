package com.desolatetimelines.piinterface.service.model;

import com.desolatetimelines.piinterface.model.PinGroup;

import lombok.Getter;
import lombok.Setter;

public class PinGroupWithState extends PinGroup {

	@Getter
	@Setter
	int currentOrder;

	public PinGroupWithState(PinGroup pinGroup) {
		this.setId(pinGroup.getId());
		this.setCurrentOrder(0);		
		this.setName(pinGroup.getName());
		this.setType(pinGroup.getType());
	}
}
