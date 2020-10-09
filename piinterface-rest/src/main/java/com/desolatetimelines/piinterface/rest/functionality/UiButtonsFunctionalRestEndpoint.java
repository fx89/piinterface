package com.desolatetimelines.piinterface.rest.functionality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desolatetimelines.piinterface.model.UiButton;
import com.desolatetimelines.piinterface.service.PiInterfaceService;
import com.desolatetimelines.piinterface.service.model.UiButtonWithState;

@RestController
@RequestMapping(value = "/api/uiButtons")
public class UiButtonsFunctionalRestEndpoint {

	@Autowired
	private PiInterfaceService piInterface;

	@GetMapping(value = "/findAllIncudingState")
	public Iterable<UiButtonWithState> findAllIncudingState() {
		return piInterface.getUiButtonsRepository().values();
	}

	@GetMapping(value = "/clickButton")
	public UiButtonWithState clickButton(@RequestParam() Long buttonId) {
		return piInterface.clickButton(buttonId);
	}

	@PostMapping(value = "/save")
	public UiButtonWithState save(@RequestBody() UiButton uiButton) {
		return piInterface.saveUiButton(uiButton);
	}

	@DeleteMapping(value = "/delete")
	public void delete(@RequestParam(name = "id") Long id) {
		piInterface.deleteUiButton(id);
	}
}
