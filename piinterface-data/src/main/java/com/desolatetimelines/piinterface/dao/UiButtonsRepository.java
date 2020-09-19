package com.desolatetimelines.piinterface.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.desolatetimelines.piinterface.model.UiButton;

public interface UiButtonsRepository extends PagingAndSortingRepository<UiButton, Long> {

	@Modifying
	@Query("delete from UiButton x where x.id in ?1")
	void bulkDelete(List<Long> ids);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByTitleIn(List<String> titles);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByTitle(String title, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByTitle(String title);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByTitle(String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByIconId(Long iconId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByIconId(Long iconId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByIconIdIn(List<Long> iconIds, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByIconIdIn(List<Long> iconIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByIconIdAndTitle(Long iconId, String title, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByIconIdAndTitle(Long iconId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByIconIdAndTitle(Long iconId, String title);
					
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByIconIdAndTypeId(Long iconId, Long typeId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByIconIdAndTypeId(Long iconId, Long typeId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByIconIdAndTypeId(Long iconId, Long typeId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByIconIdAndTypeIdAndTitle(Long iconId, Long typeId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByIconIdAndTypeIdAndTitle(Long iconId, Long typeId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByIconIdAndLinkedToPinId(Long iconId, Long linkedToPinId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByIconIdAndLinkedToPinId(Long iconId, Long linkedToPinId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByIconIdAndLinkedToPinId(Long iconId, Long linkedToPinId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByIconIdAndLinkedToPinIdAndTitle(Long iconId, Long linkedToPinId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByIconIdAndLinkedToPinIdAndTitle(Long iconId, Long linkedToPinId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByIconIdAndLinkedToPinGroupId(Long iconId, Long linkedToPinGroupId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByIconIdAndLinkedToPinGroupId(Long iconId, Long linkedToPinGroupId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByIconIdAndLinkedToPinGroupId(Long iconId, Long linkedToPinGroupId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByIconIdAndLinkedToPinGroupIdAndTitle(Long iconId, Long linkedToPinGroupId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByIconIdAndLinkedToPinGroupIdAndTitle(Long iconId, Long linkedToPinGroupId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByIconName(String name);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByIconName(String name, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByIconContent(String content);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByIconContent(String content, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByTypeId(Long typeId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByTypeId(Long typeId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByTypeIdIn(List<Long> typeIds, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByTypeIdIn(List<Long> typeIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByTypeIdAndTitle(Long typeId, String title, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByTypeIdAndTitle(Long typeId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByTypeIdAndTitle(Long typeId, String title);
					
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByTypeIdAndIconId(Long typeId, Long iconId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByTypeIdAndIconId(Long typeId, Long iconId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByTypeIdAndIconId(Long typeId, Long iconId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByTypeIdAndIconIdAndTitle(Long typeId, Long iconId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByTypeIdAndIconIdAndTitle(Long typeId, Long iconId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByTypeIdAndLinkedToPinId(Long typeId, Long linkedToPinId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByTypeIdAndLinkedToPinId(Long typeId, Long linkedToPinId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByTypeIdAndLinkedToPinId(Long typeId, Long linkedToPinId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByTypeIdAndLinkedToPinIdAndTitle(Long typeId, Long linkedToPinId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByTypeIdAndLinkedToPinIdAndTitle(Long typeId, Long linkedToPinId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByTypeIdAndLinkedToPinGroupId(Long typeId, Long linkedToPinGroupId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByTypeIdAndLinkedToPinGroupId(Long typeId, Long linkedToPinGroupId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByTypeIdAndLinkedToPinGroupId(Long typeId, Long linkedToPinGroupId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByTypeIdAndLinkedToPinGroupIdAndTitle(Long typeId, Long linkedToPinGroupId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByTypeIdAndLinkedToPinGroupIdAndTitle(Long typeId, Long linkedToPinGroupId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByTypeName(String name);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByTypeName(String name, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinId(Long linkedToPinId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinId(Long linkedToPinId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinIdIn(List<Long> linkedToPinIds, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinIdIn(List<Long> linkedToPinIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinIdAndTitle(Long linkedToPinId, String title, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinIdAndTitle(Long linkedToPinId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByLinkedToPinIdAndTitle(Long linkedToPinId, String title);
					
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinIdAndIconId(Long linkedToPinId, Long iconId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinIdAndIconId(Long linkedToPinId, Long iconId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByLinkedToPinIdAndIconId(Long linkedToPinId, Long iconId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinIdAndIconIdAndTitle(Long linkedToPinId, Long iconId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByLinkedToPinIdAndIconIdAndTitle(Long linkedToPinId, Long iconId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinIdAndTypeId(Long linkedToPinId, Long typeId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinIdAndTypeId(Long linkedToPinId, Long typeId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByLinkedToPinIdAndTypeId(Long linkedToPinId, Long typeId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinIdAndTypeIdAndTitle(Long linkedToPinId, Long typeId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByLinkedToPinIdAndTypeIdAndTitle(Long linkedToPinId, Long typeId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinIdAndLinkedToPinGroupId(Long linkedToPinId, Long linkedToPinGroupId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinIdAndLinkedToPinGroupId(Long linkedToPinId, Long linkedToPinGroupId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByLinkedToPinIdAndLinkedToPinGroupId(Long linkedToPinId, Long linkedToPinGroupId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinIdAndLinkedToPinGroupIdAndTitle(Long linkedToPinId, Long linkedToPinGroupId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByLinkedToPinIdAndLinkedToPinGroupIdAndTitle(Long linkedToPinId, Long linkedToPinGroupId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdIn(List<Long> piInstanceIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinPiInstanceId(Long piInstanceId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinPiInstanceId(Long piInstanceId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdAndTitle(Long piInstanceId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdInAndTitle(List<Long> piInstanceIds, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdInAndIconId(List<Long> piInstanceIds, Long iconId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdAndIconIdIn(Long piInstanceId, List<Long> iconIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdAndIconNotNull(Long piInstanceId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdInAndIconNotNull(List<Long> piInstanceId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdInAndIconName(List<Long> piInstanceIds, String name);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdInAndIconContent(List<Long> piInstanceIds, String content);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdInAndTypeId(List<Long> piInstanceIds, Long typeId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdAndTypeIdIn(Long piInstanceId, List<Long> typeIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdAndTypeNotNull(Long piInstanceId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdInAndTypeNotNull(List<Long> piInstanceId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdInAndTypeName(List<Long> piInstanceIds, String name);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdInAndLinkedToPinGroupId(List<Long> piInstanceIds, Long linkedToPinGroupId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdAndLinkedToPinGroupIdIn(Long piInstanceId, List<Long> linkedToPinGroupIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdAndLinkedToPinGroupNotNull(Long piInstanceId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdInAndLinkedToPinGroupNotNull(List<Long> piInstanceId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdInAndLinkedToPinGroupName(List<Long> piInstanceIds, String name);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinName(String name);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinName(String name, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinBoardId(Long boardId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinBoardId(Long boardId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGpioId(Long gpioId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGpioId(Long gpioId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdIn(List<Long> operatingModeIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinOperatingModeId(Long operatingModeId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinOperatingModeId(Long operatingModeId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdAndTitle(Long operatingModeId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdInAndTitle(List<Long> operatingModeIds, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdInAndIconId(List<Long> operatingModeIds, Long iconId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdAndIconIdIn(Long operatingModeId, List<Long> iconIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdAndIconNotNull(Long operatingModeId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdInAndIconNotNull(List<Long> operatingModeId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdInAndIconName(List<Long> operatingModeIds, String name);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdInAndIconContent(List<Long> operatingModeIds, String content);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdInAndTypeId(List<Long> operatingModeIds, Long typeId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdAndTypeIdIn(Long operatingModeId, List<Long> typeIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdAndTypeNotNull(Long operatingModeId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdInAndTypeNotNull(List<Long> operatingModeId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdInAndTypeName(List<Long> operatingModeIds, String name);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdInAndLinkedToPinGroupId(List<Long> operatingModeIds, Long linkedToPinGroupId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdAndLinkedToPinGroupIdIn(Long operatingModeId, List<Long> linkedToPinGroupIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdAndLinkedToPinGroupNotNull(Long operatingModeId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdInAndLinkedToPinGroupNotNull(List<Long> operatingModeId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdInAndLinkedToPinGroupName(List<Long> operatingModeIds, String name);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinDelayMs(Integer delayMs);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinDelayMs(Integer delayMs, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinIsAvailable(Boolean isAvailable);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinIsAvailable(Boolean isAvailable, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupId(Long linkedToPinGroupId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupId(Long linkedToPinGroupId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupIdIn(List<Long> linkedToPinGroupIds, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupIdIn(List<Long> linkedToPinGroupIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupIdAndTitle(Long linkedToPinGroupId, String title, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupIdAndTitle(Long linkedToPinGroupId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByLinkedToPinGroupIdAndTitle(Long linkedToPinGroupId, String title);
					
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupIdAndIconId(Long linkedToPinGroupId, Long iconId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupIdAndIconId(Long linkedToPinGroupId, Long iconId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByLinkedToPinGroupIdAndIconId(Long linkedToPinGroupId, Long iconId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupIdAndIconIdAndTitle(Long linkedToPinGroupId, Long iconId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByLinkedToPinGroupIdAndIconIdAndTitle(Long linkedToPinGroupId, Long iconId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupIdAndTypeId(Long linkedToPinGroupId, Long typeId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupIdAndTypeId(Long linkedToPinGroupId, Long typeId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByLinkedToPinGroupIdAndTypeId(Long linkedToPinGroupId, Long typeId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupIdAndTypeIdAndTitle(Long linkedToPinGroupId, Long typeId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByLinkedToPinGroupIdAndTypeIdAndTitle(Long linkedToPinGroupId, Long typeId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupIdAndLinkedToPinId(Long linkedToPinGroupId, Long linkedToPinId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupIdAndLinkedToPinId(Long linkedToPinGroupId, Long linkedToPinId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByLinkedToPinGroupIdAndLinkedToPinId(Long linkedToPinGroupId, Long linkedToPinId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupIdAndLinkedToPinIdAndTitle(Long linkedToPinGroupId, Long linkedToPinId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	UiButton findFirstByLinkedToPinGroupIdAndLinkedToPinIdAndTitle(Long linkedToPinGroupId, Long linkedToPinId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupName(String name);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupName(String name, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdIn(List<Long> typeIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeId(Long typeId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeId(Long typeId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdAndTitle(Long typeId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndTitle(List<Long> typeIds, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndIconId(List<Long> typeIds, Long iconId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdAndIconIdIn(Long typeId, List<Long> iconIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdAndIconNotNull(Long typeId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndIconNotNull(List<Long> typeId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndIconName(List<Long> typeIds, String name);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndIconContent(List<Long> typeIds, String content);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndTypeId(List<Long> typeIds, Long typeId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdAndTypeIdIn(Long typeId, List<Long> typeIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdAndTypeNotNull(Long typeId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndTypeNotNull(List<Long> typeId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndTypeName(List<Long> typeIds, String name);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndLinkedToPinId(List<Long> typeIds, Long linkedToPinId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdAndLinkedToPinIdIn(Long typeId, List<Long> linkedToPinIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdAndLinkedToPinNotNull(Long typeId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndLinkedToPinNotNull(List<Long> typeId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndLinkedToPinName(List<Long> typeIds, String name);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndLinkedToPinBoardId(List<Long> typeIds, Long boardId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndLinkedToPinGpioId(List<Long> typeIds, Long gpioId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndLinkedToPinDelayMs(List<Long> typeIds, Integer delayMs);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndLinkedToPinIsAvailable(List<Long> typeIds, Boolean isAvailable);

}
