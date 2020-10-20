package com.desolatetimelines.piinterface.service.model;

import com.desolatetimelines.piinterface.model.Pin;

import lombok.Getter;
import lombok.Setter;

public class PinWithState extends Pin {

	@Getter
	@Setter
	private Long currentState;

	public PinWithState(Pin fromPin) {
		super(
			fromPin.getId(),
			fromPin.getPiInstance(),
			fromPin.getName(),
			fromPin.getBoardId(),
			fromPin.getGpioId(),
			fromPin.getOperatingMode(),
			fromPin.getDelayMs(),
			fromPin.getIsAvailable(),
			fromPin.getStatesCount()
		);

		this.currentState = 0L;
	}
}
