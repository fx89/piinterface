package com.desolatetimelines.piinterface.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desolatetimelines.piinterface.model.UiButton;
import com.desolatetimelines.piinterface.rest.exception.PiInterfaceRESTException;
import com.desolatetimelines.piinterface.service.PiInterfaceService;

@RestController
@RequestMapping(value = "/api/uiButtons")
public class UiButtonsRestEndpoint {
	@Autowired
	private PiInterfaceService piInterface;

	@GetMapping(value = "/count")
	public Long count() {
		return piInterface.getDataService().getUiButtonsRepository().count();
	}

	@GetMapping(value = "/findOneById")
	public UiButton findOneById(@RequestParam(name = "id") Long id) {
		return piInterface.getDataService().getUiButtonsRepository().findById(id).orElseThrow(
				() -> new PiInterfaceRESTException("Could not find ui button with id = [" + id + "]"));
	}

	@GetMapping(value = "/findAll")
	public Iterable<UiButton> findAll() {
		return piInterface.getDataService().getUiButtonsRepository().findAll();
	}

	@GetMapping(value = "/pageAll")
	public Iterable<UiButton> pageAll(@RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAll(PageRequest.of(pageNumber, pageSize)).getContent();
	}

	@PostMapping(value = "/save")
	public UiButton save(@RequestBody UiButton item) {
		return piInterface.getDataService().getUiButtonsRepository().save(item);
	}

	@Transactional
	@DeleteMapping(value = "/delete")
	public void delete(@RequestParam(name = "id") Long id) {
		piInterface.getDataService().getUiButtonsRepository().deleteById(id);
	}

	@Transactional
	@DeleteMapping(value = "/bulkDelete")
	public void bulkDelete(@RequestBody List<Long> ids) {
		piInterface.getDataService().getUiButtonsRepository().bulkDelete(ids);
	}

	@GetMapping(value = "/pageAllByTitle")
	public List<UiButton> pageAllByTitle(@RequestParam(name = "title") String title, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByTitle(title, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByTitle")
	public List<UiButton> findAllByTitle(@RequestParam(name = "title") String title) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByTitle(title);
	}

	@GetMapping(value = "/pageAllByIconId")
	public List<UiButton> pageAllByIconId(@RequestParam(name = "iconId") Long iconId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByIconId(iconId, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByIconId")
	public List<UiButton> findAllByIconId(@RequestParam(name = "iconId") Long iconId) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByIconId(iconId);
	}
	
	@GetMapping(value = "/pageAllByIconIdIn")
	public List<UiButton> pageAllByIconIdIn(@RequestBody() List<Long> iconIds, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByIconIdIn(iconIds, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByIconIdIn")
	public List<UiButton> findAllByIconIdIn(@RequestBody() List<Long> iconIds) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByIconIdIn(iconIds);
	}

	
	@GetMapping(value = "/findAllByIconIdAndTitle")
	public List<UiButton> findAllByIconIdAndTitle(@RequestParam(name = "iconId") Long iconId, @RequestParam(name = "title") String title) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByIconIdAndTitle(iconId, title);
	}
	
	@GetMapping(value = "/findFirstByIconIdAndTitle")
	public UiButton findFirstByIconIdAndTitle(@RequestParam(name = "iconId") Long iconId, @RequestParam(name = "title") String title) {
		return piInterface.getDataService().getUiButtonsRepository().findFirstByIconIdAndTitle(iconId, title);
	}

	@GetMapping(value = "/pageAllByIconIdAndTypeId")
	public List<UiButton> pageAllByIconIdAndTypeId(@RequestParam(name = "iconId") Long iconId, @RequestParam(name = "typeId") Long typeId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByIconIdAndTypeId(iconId, typeId, PageRequest.of(pageNumber, pageSize));
	}
	
	@GetMapping(value = "/findAllByIconIdAndTypeId")
	public List<UiButton> findAllByIconIdAndTypeId(@RequestParam(name = "iconId") Long iconId, @RequestParam(name = "typeId") Long typeId) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByIconIdAndTypeId(iconId, typeId);
	}
	
	@GetMapping(value = "/findFirstByIconIdAndTypeId")
	public UiButton findFirstByIconIdAndTypeId(@RequestParam(name = "iconId") Long iconId, @RequestParam(name = "typeId") Long typeId) {
		return piInterface.getDataService().getUiButtonsRepository().findFirstByIconIdAndTypeId(iconId, typeId);
	}

	@GetMapping(value = "/findAllByIconIdAndTypeIdAndTitle")
	List<UiButton> findAllByIconIdAndTypeIdAndTitle(@RequestParam(name = "iconId") Long iconId, @RequestParam(name = "typeId") Long typeId, @RequestParam(name = "title") String title) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByIconIdAndTypeIdAndTitle(iconId, typeId, title);
	}

	@GetMapping(value = "/pageAllByIconIdAndLinkedToPinId")
	public List<UiButton> pageAllByIconIdAndLinkedToPinId(@RequestParam(name = "iconId") Long iconId, @RequestParam(name = "linkedToPinId") Long linkedToPinId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByIconIdAndLinkedToPinId(iconId, linkedToPinId, PageRequest.of(pageNumber, pageSize));
	}
	
	@GetMapping(value = "/findAllByIconIdAndLinkedToPinId")
	public List<UiButton> findAllByIconIdAndLinkedToPinId(@RequestParam(name = "iconId") Long iconId, @RequestParam(name = "linkedToPinId") Long linkedToPinId) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByIconIdAndLinkedToPinId(iconId, linkedToPinId);
	}
	
	@GetMapping(value = "/findFirstByIconIdAndLinkedToPinId")
	public UiButton findFirstByIconIdAndLinkedToPinId(@RequestParam(name = "iconId") Long iconId, @RequestParam(name = "linkedToPinId") Long linkedToPinId) {
		return piInterface.getDataService().getUiButtonsRepository().findFirstByIconIdAndLinkedToPinId(iconId, linkedToPinId);
	}

	@GetMapping(value = "/findAllByIconIdAndLinkedToPinIdAndTitle")
	List<UiButton> findAllByIconIdAndLinkedToPinIdAndTitle(@RequestParam(name = "iconId") Long iconId, @RequestParam(name = "linkedToPinId") Long linkedToPinId, @RequestParam(name = "title") String title) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByIconIdAndLinkedToPinIdAndTitle(iconId, linkedToPinId, title);
	}

	@GetMapping(value = "/pageAllByIconIdAndLinkedToPinGroupId")
	public List<UiButton> pageAllByIconIdAndLinkedToPinGroupId(@RequestParam(name = "iconId") Long iconId, @RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByIconIdAndLinkedToPinGroupId(iconId, linkedToPinGroupId, PageRequest.of(pageNumber, pageSize));
	}
	
	@GetMapping(value = "/findAllByIconIdAndLinkedToPinGroupId")
	public List<UiButton> findAllByIconIdAndLinkedToPinGroupId(@RequestParam(name = "iconId") Long iconId, @RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByIconIdAndLinkedToPinGroupId(iconId, linkedToPinGroupId);
	}
	
	@GetMapping(value = "/findFirstByIconIdAndLinkedToPinGroupId")
	public UiButton findFirstByIconIdAndLinkedToPinGroupId(@RequestParam(name = "iconId") Long iconId, @RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId) {
		return piInterface.getDataService().getUiButtonsRepository().findFirstByIconIdAndLinkedToPinGroupId(iconId, linkedToPinGroupId);
	}

	@GetMapping(value = "/findAllByIconIdAndLinkedToPinGroupIdAndTitle")
	List<UiButton> findAllByIconIdAndLinkedToPinGroupIdAndTitle(@RequestParam(name = "iconId") Long iconId, @RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId, @RequestParam(name = "title") String title) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByIconIdAndLinkedToPinGroupIdAndTitle(iconId, linkedToPinGroupId, title);
	}

	@GetMapping(value = "/findAllByIconName")
	public List<UiButton> findAllByIconName(@RequestParam(name="iconName") String name) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByIconName(name);
	}
	
	@GetMapping(value = "/pageAllByIconName")
	public List<UiButton> pageAllByIconName(@RequestParam(name="iconName") String name, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByIconName(name, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByIconContent")
	public List<UiButton> findAllByIconContent(@RequestParam(name="iconContent") String content) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByIconContent(content);
	}
	
	@GetMapping(value = "/pageAllByIconContent")
	public List<UiButton> pageAllByIconContent(@RequestParam(name="iconContent") String content, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByIconContent(content, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/pageAllByTypeId")
	public List<UiButton> pageAllByTypeId(@RequestParam(name = "typeId") Long typeId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByTypeId(typeId, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByTypeId")
	public List<UiButton> findAllByTypeId(@RequestParam(name = "typeId") Long typeId) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByTypeId(typeId);
	}
	
	@GetMapping(value = "/pageAllByTypeIdIn")
	public List<UiButton> pageAllByTypeIdIn(@RequestBody() List<Long> typeIds, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByTypeIdIn(typeIds, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByTypeIdIn")
	public List<UiButton> findAllByTypeIdIn(@RequestBody() List<Long> typeIds) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByTypeIdIn(typeIds);
	}

	
	@GetMapping(value = "/findAllByTypeIdAndTitle")
	public List<UiButton> findAllByTypeIdAndTitle(@RequestParam(name = "typeId") Long typeId, @RequestParam(name = "title") String title) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByTypeIdAndTitle(typeId, title);
	}
	
	@GetMapping(value = "/findFirstByTypeIdAndTitle")
	public UiButton findFirstByTypeIdAndTitle(@RequestParam(name = "typeId") Long typeId, @RequestParam(name = "title") String title) {
		return piInterface.getDataService().getUiButtonsRepository().findFirstByTypeIdAndTitle(typeId, title);
	}

	@GetMapping(value = "/pageAllByTypeIdAndIconId")
	public List<UiButton> pageAllByTypeIdAndIconId(@RequestParam(name = "typeId") Long typeId, @RequestParam(name = "iconId") Long iconId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByTypeIdAndIconId(typeId, iconId, PageRequest.of(pageNumber, pageSize));
	}
	
	@GetMapping(value = "/findAllByTypeIdAndIconId")
	public List<UiButton> findAllByTypeIdAndIconId(@RequestParam(name = "typeId") Long typeId, @RequestParam(name = "iconId") Long iconId) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByTypeIdAndIconId(typeId, iconId);
	}
	
	@GetMapping(value = "/findFirstByTypeIdAndIconId")
	public UiButton findFirstByTypeIdAndIconId(@RequestParam(name = "typeId") Long typeId, @RequestParam(name = "iconId") Long iconId) {
		return piInterface.getDataService().getUiButtonsRepository().findFirstByTypeIdAndIconId(typeId, iconId);
	}

	@GetMapping(value = "/findAllByTypeIdAndIconIdAndTitle")
	List<UiButton> findAllByTypeIdAndIconIdAndTitle(@RequestParam(name = "typeId") Long typeId, @RequestParam(name = "iconId") Long iconId, @RequestParam(name = "title") String title) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByTypeIdAndIconIdAndTitle(typeId, iconId, title);
	}

	@GetMapping(value = "/pageAllByTypeIdAndLinkedToPinId")
	public List<UiButton> pageAllByTypeIdAndLinkedToPinId(@RequestParam(name = "typeId") Long typeId, @RequestParam(name = "linkedToPinId") Long linkedToPinId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByTypeIdAndLinkedToPinId(typeId, linkedToPinId, PageRequest.of(pageNumber, pageSize));
	}
	
	@GetMapping(value = "/findAllByTypeIdAndLinkedToPinId")
	public List<UiButton> findAllByTypeIdAndLinkedToPinId(@RequestParam(name = "typeId") Long typeId, @RequestParam(name = "linkedToPinId") Long linkedToPinId) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByTypeIdAndLinkedToPinId(typeId, linkedToPinId);
	}
	
	@GetMapping(value = "/findFirstByTypeIdAndLinkedToPinId")
	public UiButton findFirstByTypeIdAndLinkedToPinId(@RequestParam(name = "typeId") Long typeId, @RequestParam(name = "linkedToPinId") Long linkedToPinId) {
		return piInterface.getDataService().getUiButtonsRepository().findFirstByTypeIdAndLinkedToPinId(typeId, linkedToPinId);
	}

	@GetMapping(value = "/findAllByTypeIdAndLinkedToPinIdAndTitle")
	List<UiButton> findAllByTypeIdAndLinkedToPinIdAndTitle(@RequestParam(name = "typeId") Long typeId, @RequestParam(name = "linkedToPinId") Long linkedToPinId, @RequestParam(name = "title") String title) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByTypeIdAndLinkedToPinIdAndTitle(typeId, linkedToPinId, title);
	}

	@GetMapping(value = "/pageAllByTypeIdAndLinkedToPinGroupId")
	public List<UiButton> pageAllByTypeIdAndLinkedToPinGroupId(@RequestParam(name = "typeId") Long typeId, @RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByTypeIdAndLinkedToPinGroupId(typeId, linkedToPinGroupId, PageRequest.of(pageNumber, pageSize));
	}
	
	@GetMapping(value = "/findAllByTypeIdAndLinkedToPinGroupId")
	public List<UiButton> findAllByTypeIdAndLinkedToPinGroupId(@RequestParam(name = "typeId") Long typeId, @RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByTypeIdAndLinkedToPinGroupId(typeId, linkedToPinGroupId);
	}
	
	@GetMapping(value = "/findFirstByTypeIdAndLinkedToPinGroupId")
	public UiButton findFirstByTypeIdAndLinkedToPinGroupId(@RequestParam(name = "typeId") Long typeId, @RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId) {
		return piInterface.getDataService().getUiButtonsRepository().findFirstByTypeIdAndLinkedToPinGroupId(typeId, linkedToPinGroupId);
	}

	@GetMapping(value = "/findAllByTypeIdAndLinkedToPinGroupIdAndTitle")
	List<UiButton> findAllByTypeIdAndLinkedToPinGroupIdAndTitle(@RequestParam(name = "typeId") Long typeId, @RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId, @RequestParam(name = "title") String title) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByTypeIdAndLinkedToPinGroupIdAndTitle(typeId, linkedToPinGroupId, title);
	}

	@GetMapping(value = "/findAllByTypeName")
	public List<UiButton> findAllByTypeName(@RequestParam(name="typeName") String name) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByTypeName(name);
	}
	
	@GetMapping(value = "/pageAllByTypeName")
	public List<UiButton> pageAllByTypeName(@RequestParam(name="typeName") String name, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByTypeName(name, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/pageAllByLinkedToPinId")
	public List<UiButton> pageAllByLinkedToPinId(@RequestParam(name = "linkedToPinId") Long linkedToPinId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinId(linkedToPinId, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByLinkedToPinId")
	public List<UiButton> findAllByLinkedToPinId(@RequestParam(name = "linkedToPinId") Long linkedToPinId) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinId(linkedToPinId);
	}
	
	@GetMapping(value = "/pageAllByLinkedToPinIdIn")
	public List<UiButton> pageAllByLinkedToPinIdIn(@RequestBody() List<Long> linkedToPinIds, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinIdIn(linkedToPinIds, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByLinkedToPinIdIn")
	public List<UiButton> findAllByLinkedToPinIdIn(@RequestBody() List<Long> linkedToPinIds) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinIdIn(linkedToPinIds);
	}

	
	@GetMapping(value = "/findAllByLinkedToPinIdAndTitle")
	public List<UiButton> findAllByLinkedToPinIdAndTitle(@RequestParam(name = "linkedToPinId") Long linkedToPinId, @RequestParam(name = "title") String title) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinIdAndTitle(linkedToPinId, title);
	}
	
	@GetMapping(value = "/findFirstByLinkedToPinIdAndTitle")
	public UiButton findFirstByLinkedToPinIdAndTitle(@RequestParam(name = "linkedToPinId") Long linkedToPinId, @RequestParam(name = "title") String title) {
		return piInterface.getDataService().getUiButtonsRepository().findFirstByLinkedToPinIdAndTitle(linkedToPinId, title);
	}

	@GetMapping(value = "/pageAllByLinkedToPinIdAndIconId")
	public List<UiButton> pageAllByLinkedToPinIdAndIconId(@RequestParam(name = "linkedToPinId") Long linkedToPinId, @RequestParam(name = "iconId") Long iconId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinIdAndIconId(linkedToPinId, iconId, PageRequest.of(pageNumber, pageSize));
	}
	
	@GetMapping(value = "/findAllByLinkedToPinIdAndIconId")
	public List<UiButton> findAllByLinkedToPinIdAndIconId(@RequestParam(name = "linkedToPinId") Long linkedToPinId, @RequestParam(name = "iconId") Long iconId) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinIdAndIconId(linkedToPinId, iconId);
	}
	
	@GetMapping(value = "/findFirstByLinkedToPinIdAndIconId")
	public UiButton findFirstByLinkedToPinIdAndIconId(@RequestParam(name = "linkedToPinId") Long linkedToPinId, @RequestParam(name = "iconId") Long iconId) {
		return piInterface.getDataService().getUiButtonsRepository().findFirstByLinkedToPinIdAndIconId(linkedToPinId, iconId);
	}

	@GetMapping(value = "/findAllByLinkedToPinIdAndIconIdAndTitle")
	List<UiButton> findAllByLinkedToPinIdAndIconIdAndTitle(@RequestParam(name = "linkedToPinId") Long linkedToPinId, @RequestParam(name = "iconId") Long iconId, @RequestParam(name = "title") String title) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinIdAndIconIdAndTitle(linkedToPinId, iconId, title);
	}

	@GetMapping(value = "/pageAllByLinkedToPinIdAndTypeId")
	public List<UiButton> pageAllByLinkedToPinIdAndTypeId(@RequestParam(name = "linkedToPinId") Long linkedToPinId, @RequestParam(name = "typeId") Long typeId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinIdAndTypeId(linkedToPinId, typeId, PageRequest.of(pageNumber, pageSize));
	}
	
	@GetMapping(value = "/findAllByLinkedToPinIdAndTypeId")
	public List<UiButton> findAllByLinkedToPinIdAndTypeId(@RequestParam(name = "linkedToPinId") Long linkedToPinId, @RequestParam(name = "typeId") Long typeId) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinIdAndTypeId(linkedToPinId, typeId);
	}
	
	@GetMapping(value = "/findFirstByLinkedToPinIdAndTypeId")
	public UiButton findFirstByLinkedToPinIdAndTypeId(@RequestParam(name = "linkedToPinId") Long linkedToPinId, @RequestParam(name = "typeId") Long typeId) {
		return piInterface.getDataService().getUiButtonsRepository().findFirstByLinkedToPinIdAndTypeId(linkedToPinId, typeId);
	}

	@GetMapping(value = "/findAllByLinkedToPinIdAndTypeIdAndTitle")
	List<UiButton> findAllByLinkedToPinIdAndTypeIdAndTitle(@RequestParam(name = "linkedToPinId") Long linkedToPinId, @RequestParam(name = "typeId") Long typeId, @RequestParam(name = "title") String title) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinIdAndTypeIdAndTitle(linkedToPinId, typeId, title);
	}

	@GetMapping(value = "/pageAllByLinkedToPinIdAndLinkedToPinGroupId")
	public List<UiButton> pageAllByLinkedToPinIdAndLinkedToPinGroupId(@RequestParam(name = "linkedToPinId") Long linkedToPinId, @RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinIdAndLinkedToPinGroupId(linkedToPinId, linkedToPinGroupId, PageRequest.of(pageNumber, pageSize));
	}
	
	@GetMapping(value = "/findAllByLinkedToPinIdAndLinkedToPinGroupId")
	public List<UiButton> findAllByLinkedToPinIdAndLinkedToPinGroupId(@RequestParam(name = "linkedToPinId") Long linkedToPinId, @RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinIdAndLinkedToPinGroupId(linkedToPinId, linkedToPinGroupId);
	}
	
	@GetMapping(value = "/findFirstByLinkedToPinIdAndLinkedToPinGroupId")
	public UiButton findFirstByLinkedToPinIdAndLinkedToPinGroupId(@RequestParam(name = "linkedToPinId") Long linkedToPinId, @RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId) {
		return piInterface.getDataService().getUiButtonsRepository().findFirstByLinkedToPinIdAndLinkedToPinGroupId(linkedToPinId, linkedToPinGroupId);
	}

	@GetMapping(value = "/findAllByLinkedToPinIdAndLinkedToPinGroupIdAndTitle")
	List<UiButton> findAllByLinkedToPinIdAndLinkedToPinGroupIdAndTitle(@RequestParam(name = "linkedToPinId") Long linkedToPinId, @RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId, @RequestParam(name = "title") String title) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinIdAndLinkedToPinGroupIdAndTitle(linkedToPinId, linkedToPinGroupId, title);
	}

	@GetMapping(value = "/findAllByLinkedToPinName")
	public List<UiButton> findAllByLinkedToPinName(@RequestParam(name="linkedToPinName") String name) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinName(name);
	}
	
	@GetMapping(value = "/pageAllByLinkedToPinName")
	public List<UiButton> pageAllByLinkedToPinName(@RequestParam(name="linkedToPinName") String name, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinName(name, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByLinkedToPinBoardId")
	public List<UiButton> findAllByLinkedToPinBoardId(@RequestParam(name="linkedToPinBoardId") Long boardId) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinBoardId(boardId);
	}
	
	@GetMapping(value = "/pageAllByLinkedToPinBoardId")
	public List<UiButton> pageAllByLinkedToPinBoardId(@RequestParam(name="linkedToPinBoardId") Long boardId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinBoardId(boardId, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByLinkedToPinGpioId")
	public List<UiButton> findAllByLinkedToPinGpioId(@RequestParam(name="linkedToPinGpioId") Long gpioId) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinGpioId(gpioId);
	}
	
	@GetMapping(value = "/pageAllByLinkedToPinGpioId")
	public List<UiButton> pageAllByLinkedToPinGpioId(@RequestParam(name="linkedToPinGpioId") Long gpioId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinGpioId(gpioId, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByLinkedToPinDelayMs")
	public List<UiButton> findAllByLinkedToPinDelayMs(@RequestParam(name="linkedToPinDelayMs") Integer delayMs) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinDelayMs(delayMs);
	}
	
	@GetMapping(value = "/pageAllByLinkedToPinDelayMs")
	public List<UiButton> pageAllByLinkedToPinDelayMs(@RequestParam(name="linkedToPinDelayMs") Integer delayMs, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinDelayMs(delayMs, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByLinkedToPinIsAvailable")
	public List<UiButton> findAllByLinkedToPinIsAvailable(@RequestParam(name="linkedToPinIsAvailable") Boolean isAvailable) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinIsAvailable(isAvailable);
	}
	
	@GetMapping(value = "/pageAllByLinkedToPinIsAvailable")
	public List<UiButton> pageAllByLinkedToPinIsAvailable(@RequestParam(name="linkedToPinIsAvailable") Boolean isAvailable, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinIsAvailable(isAvailable, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/pageAllByLinkedToPinGroupId")
	public List<UiButton> pageAllByLinkedToPinGroupId(@RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinGroupId(linkedToPinGroupId, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByLinkedToPinGroupId")
	public List<UiButton> findAllByLinkedToPinGroupId(@RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinGroupId(linkedToPinGroupId);
	}
	
	@GetMapping(value = "/pageAllByLinkedToPinGroupIdIn")
	public List<UiButton> pageAllByLinkedToPinGroupIdIn(@RequestBody() List<Long> linkedToPinGroupIds, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinGroupIdIn(linkedToPinGroupIds, PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping(value = "/findAllByLinkedToPinGroupIdIn")
	public List<UiButton> findAllByLinkedToPinGroupIdIn(@RequestBody() List<Long> linkedToPinGroupIds) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinGroupIdIn(linkedToPinGroupIds);
	}

	
	@GetMapping(value = "/findAllByLinkedToPinGroupIdAndTitle")
	public List<UiButton> findAllByLinkedToPinGroupIdAndTitle(@RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId, @RequestParam(name = "title") String title) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinGroupIdAndTitle(linkedToPinGroupId, title);
	}
	
	@GetMapping(value = "/findFirstByLinkedToPinGroupIdAndTitle")
	public UiButton findFirstByLinkedToPinGroupIdAndTitle(@RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId, @RequestParam(name = "title") String title) {
		return piInterface.getDataService().getUiButtonsRepository().findFirstByLinkedToPinGroupIdAndTitle(linkedToPinGroupId, title);
	}

	@GetMapping(value = "/pageAllByLinkedToPinGroupIdAndIconId")
	public List<UiButton> pageAllByLinkedToPinGroupIdAndIconId(@RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId, @RequestParam(name = "iconId") Long iconId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinGroupIdAndIconId(linkedToPinGroupId, iconId, PageRequest.of(pageNumber, pageSize));
	}
	
	@GetMapping(value = "/findAllByLinkedToPinGroupIdAndIconId")
	public List<UiButton> findAllByLinkedToPinGroupIdAndIconId(@RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId, @RequestParam(name = "iconId") Long iconId) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinGroupIdAndIconId(linkedToPinGroupId, iconId);
	}
	
	@GetMapping(value = "/findFirstByLinkedToPinGroupIdAndIconId")
	public UiButton findFirstByLinkedToPinGroupIdAndIconId(@RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId, @RequestParam(name = "iconId") Long iconId) {
		return piInterface.getDataService().getUiButtonsRepository().findFirstByLinkedToPinGroupIdAndIconId(linkedToPinGroupId, iconId);
	}

	@GetMapping(value = "/findAllByLinkedToPinGroupIdAndIconIdAndTitle")
	List<UiButton> findAllByLinkedToPinGroupIdAndIconIdAndTitle(@RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId, @RequestParam(name = "iconId") Long iconId, @RequestParam(name = "title") String title) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinGroupIdAndIconIdAndTitle(linkedToPinGroupId, iconId, title);
	}

	@GetMapping(value = "/pageAllByLinkedToPinGroupIdAndTypeId")
	public List<UiButton> pageAllByLinkedToPinGroupIdAndTypeId(@RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId, @RequestParam(name = "typeId") Long typeId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinGroupIdAndTypeId(linkedToPinGroupId, typeId, PageRequest.of(pageNumber, pageSize));
	}
	
	@GetMapping(value = "/findAllByLinkedToPinGroupIdAndTypeId")
	public List<UiButton> findAllByLinkedToPinGroupIdAndTypeId(@RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId, @RequestParam(name = "typeId") Long typeId) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinGroupIdAndTypeId(linkedToPinGroupId, typeId);
	}
	
	@GetMapping(value = "/findFirstByLinkedToPinGroupIdAndTypeId")
	public UiButton findFirstByLinkedToPinGroupIdAndTypeId(@RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId, @RequestParam(name = "typeId") Long typeId) {
		return piInterface.getDataService().getUiButtonsRepository().findFirstByLinkedToPinGroupIdAndTypeId(linkedToPinGroupId, typeId);
	}

	@GetMapping(value = "/findAllByLinkedToPinGroupIdAndTypeIdAndTitle")
	List<UiButton> findAllByLinkedToPinGroupIdAndTypeIdAndTitle(@RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId, @RequestParam(name = "typeId") Long typeId, @RequestParam(name = "title") String title) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinGroupIdAndTypeIdAndTitle(linkedToPinGroupId, typeId, title);
	}

	@GetMapping(value = "/pageAllByLinkedToPinGroupIdAndLinkedToPinId")
	public List<UiButton> pageAllByLinkedToPinGroupIdAndLinkedToPinId(@RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId, @RequestParam(name = "linkedToPinId") Long linkedToPinId, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinGroupIdAndLinkedToPinId(linkedToPinGroupId, linkedToPinId, PageRequest.of(pageNumber, pageSize));
	}
	
	@GetMapping(value = "/findAllByLinkedToPinGroupIdAndLinkedToPinId")
	public List<UiButton> findAllByLinkedToPinGroupIdAndLinkedToPinId(@RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId, @RequestParam(name = "linkedToPinId") Long linkedToPinId) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinGroupIdAndLinkedToPinId(linkedToPinGroupId, linkedToPinId);
	}
	
	@GetMapping(value = "/findFirstByLinkedToPinGroupIdAndLinkedToPinId")
	public UiButton findFirstByLinkedToPinGroupIdAndLinkedToPinId(@RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId, @RequestParam(name = "linkedToPinId") Long linkedToPinId) {
		return piInterface.getDataService().getUiButtonsRepository().findFirstByLinkedToPinGroupIdAndLinkedToPinId(linkedToPinGroupId, linkedToPinId);
	}

	@GetMapping(value = "/findAllByLinkedToPinGroupIdAndLinkedToPinIdAndTitle")
	List<UiButton> findAllByLinkedToPinGroupIdAndLinkedToPinIdAndTitle(@RequestParam(name = "linkedToPinGroupId") Long linkedToPinGroupId, @RequestParam(name = "linkedToPinId") Long linkedToPinId, @RequestParam(name = "title") String title) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinGroupIdAndLinkedToPinIdAndTitle(linkedToPinGroupId, linkedToPinId, title);
	}

	@GetMapping(value = "/findAllByLinkedToPinGroupName")
	public List<UiButton> findAllByLinkedToPinGroupName(@RequestParam(name="linkedToPinGroupName") String name) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinGroupName(name);
	}
	
	@GetMapping(value = "/pageAllByLinkedToPinGroupName")
	public List<UiButton> pageAllByLinkedToPinGroupName(@RequestParam(name="linkedToPinGroupName") String name, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		return piInterface.getDataService().getUiButtonsRepository().findAllByLinkedToPinGroupName(name, PageRequest.of(pageNumber, pageSize));
	}

}
