package com.desolatetimelines.piinterface.rest.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desolatetimelines.piinterface.model.UiButtonsPanel;
import com.desolatetimelines.piinterface.service.PiInterfaceService;

@RestController
@RequestMapping(value = "/api/uiButtonPanels")
public class UiButtonPanelsRestEndpoint {
	@Autowired
	private PiInterfaceService piInterface;

	@GetMapping(value = "/findAll")
	public Iterable<UiButtonsPanel> findAll() {
		return piInterface.getDataService().getUiButtonsPanelsRepository().findAll();
	}

	@PostMapping(value = "/save")
	public UiButtonsPanel save(@RequestBody UiButtonsPanel item) {
		return piInterface.getDataService().getUiButtonsPanelsRepository().save(item);
	}
}
