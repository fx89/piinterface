package com.desolatetimelines.piinterface.service.model;

import com.desolatetimelines.piinterface.model.UiButton;

import lombok.Getter;
import lombok.Setter;

public class UiButtonWithState extends UiButton {
	@Getter
	@Setter
	private int state;

	public UiButtonWithState(UiButton fromUiButton, int state) {
		super();
		this.setId(fromUiButton.getId());
		this.setIconId(fromUiButton.getIconId());
		this.setLinkedToPin(fromUiButton.getLinkedToPin());
		this.setLinkedToPinGroup(fromUiButton.getLinkedToPinGroup());
		this.setState(state);
		this.setTitle(fromUiButton.getTitle());
		this.setType(fromUiButton.getType());
		this.setOrder(fromUiButton.getOrder());
	}
}
