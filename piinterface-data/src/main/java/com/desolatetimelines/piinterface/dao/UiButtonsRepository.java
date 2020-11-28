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
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByTitleIn(List<String> titles);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByTitle(String title, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByTitle(String title);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByTitle(String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByIconId(Long iconId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByIconId(Long iconId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByIconIdIn(List<Long> iconIds, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByIconIdIn(List<Long> iconIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByIconIdAndTitle(Long iconId, String title, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByIconIdAndTitle(Long iconId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByIconIdAndTitle(Long iconId, String title);
					
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByIconIdAndTypeId(Long iconId, Long typeId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByIconIdAndTypeId(Long iconId, Long typeId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByIconIdAndTypeId(Long iconId, Long typeId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByIconIdAndTypeIdAndTitle(Long iconId, Long typeId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByIconIdAndTypeIdAndTitle(Long iconId, Long typeId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByIconIdAndLinkedToPinId(Long iconId, Long linkedToPinId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByIconIdAndLinkedToPinId(Long iconId, Long linkedToPinId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByIconIdAndLinkedToPinId(Long iconId, Long linkedToPinId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByIconIdAndLinkedToPinIdAndTitle(Long iconId, Long linkedToPinId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByIconIdAndLinkedToPinIdAndTitle(Long iconId, Long linkedToPinId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByIconIdAndLinkedToPinGroupId(Long iconId, Long linkedToPinGroupId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByIconIdAndLinkedToPinGroupId(Long iconId, Long linkedToPinGroupId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByIconIdAndLinkedToPinGroupId(Long iconId, Long linkedToPinGroupId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByIconIdAndLinkedToPinGroupIdAndTitle(Long iconId, Long linkedToPinGroupId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByIconIdAndLinkedToPinGroupIdAndTitle(Long iconId, Long linkedToPinGroupId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByTypeId(Long typeId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByTypeId(Long typeId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByTypeIdIn(List<Long> typeIds, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByTypeIdIn(List<Long> typeIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByTypeIdAndTitle(Long typeId, String title, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByTypeIdAndTitle(Long typeId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByTypeIdAndTitle(Long typeId, String title);
					
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByTypeIdAndIconId(Long typeId, Long iconId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByTypeIdAndIconId(Long typeId, Long iconId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByTypeIdAndIconId(Long typeId, Long iconId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByTypeIdAndIconIdAndTitle(Long typeId, Long iconId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByTypeIdAndIconIdAndTitle(Long typeId, Long iconId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByTypeIdAndLinkedToPinId(Long typeId, Long linkedToPinId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByTypeIdAndLinkedToPinId(Long typeId, Long linkedToPinId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByTypeIdAndLinkedToPinId(Long typeId, Long linkedToPinId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByTypeIdAndLinkedToPinIdAndTitle(Long typeId, Long linkedToPinId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByTypeIdAndLinkedToPinIdAndTitle(Long typeId, Long linkedToPinId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByTypeIdAndLinkedToPinGroupId(Long typeId, Long linkedToPinGroupId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByTypeIdAndLinkedToPinGroupId(Long typeId, Long linkedToPinGroupId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByTypeIdAndLinkedToPinGroupId(Long typeId, Long linkedToPinGroupId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByTypeIdAndLinkedToPinGroupIdAndTitle(Long typeId, Long linkedToPinGroupId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByTypeIdAndLinkedToPinGroupIdAndTitle(Long typeId, Long linkedToPinGroupId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByTypeName(String name);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByTypeName(String name, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinId(Long linkedToPinId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinId(Long linkedToPinId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinIdIn(List<Long> linkedToPinIds, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinIdIn(List<Long> linkedToPinIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinIdAndTitle(Long linkedToPinId, String title, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinIdAndTitle(Long linkedToPinId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByLinkedToPinIdAndTitle(Long linkedToPinId, String title);
					
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinIdAndIconId(Long linkedToPinId, Long iconId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinIdAndIconId(Long linkedToPinId, Long iconId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByLinkedToPinIdAndIconId(Long linkedToPinId, Long iconId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinIdAndIconIdAndTitle(Long linkedToPinId, Long iconId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByLinkedToPinIdAndIconIdAndTitle(Long linkedToPinId, Long iconId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinIdAndTypeId(Long linkedToPinId, Long typeId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinIdAndTypeId(Long linkedToPinId, Long typeId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByLinkedToPinIdAndTypeId(Long linkedToPinId, Long typeId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinIdAndTypeIdAndTitle(Long linkedToPinId, Long typeId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByLinkedToPinIdAndTypeIdAndTitle(Long linkedToPinId, Long typeId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinIdAndLinkedToPinGroupId(Long linkedToPinId, Long linkedToPinGroupId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinIdAndLinkedToPinGroupId(Long linkedToPinId, Long linkedToPinGroupId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByLinkedToPinIdAndLinkedToPinGroupId(Long linkedToPinId, Long linkedToPinGroupId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinIdAndLinkedToPinGroupIdAndTitle(Long linkedToPinId, Long linkedToPinGroupId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByLinkedToPinIdAndLinkedToPinGroupIdAndTitle(Long linkedToPinId, Long linkedToPinGroupId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdIn(List<Long> piInstanceIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinPiInstanceId(Long piInstanceId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinPiInstanceId(Long piInstanceId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdAndTitle(Long piInstanceId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdInAndTitle(List<Long> piInstanceIds, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdInAndIconId(List<Long> piInstanceIds, Long iconId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdAndIconIdIn(Long piInstanceId, List<Long> iconIds);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdInAndTypeId(List<Long> piInstanceIds, Long typeId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdAndTypeIdIn(Long piInstanceId, List<Long> typeIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdAndTypeNotNull(Long piInstanceId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdInAndTypeNotNull(List<Long> piInstanceId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdInAndTypeName(List<Long> piInstanceIds, String name);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdInAndLinkedToPinGroupId(List<Long> piInstanceIds, Long linkedToPinGroupId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdAndLinkedToPinGroupIdIn(Long piInstanceId, List<Long> linkedToPinGroupIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdAndLinkedToPinGroupNotNull(Long piInstanceId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdInAndLinkedToPinGroupNotNull(List<Long> piInstanceId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinPiInstanceIdInAndLinkedToPinGroupName(List<Long> piInstanceIds, String name);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinName(String name);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinName(String name, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinBoardId(Long boardId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinBoardId(Long boardId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGpioId(Long gpioId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGpioId(Long gpioId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdIn(List<Long> operatingModeIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinOperatingModeId(Long operatingModeId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinOperatingModeId(Long operatingModeId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdAndTitle(Long operatingModeId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdInAndTitle(List<Long> operatingModeIds, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdInAndIconId(List<Long> operatingModeIds, Long iconId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdAndIconIdIn(Long operatingModeId, List<Long> iconIds);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdInAndTypeId(List<Long> operatingModeIds, Long typeId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdAndTypeIdIn(Long operatingModeId, List<Long> typeIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdAndTypeNotNull(Long operatingModeId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdInAndTypeNotNull(List<Long> operatingModeId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdInAndTypeName(List<Long> operatingModeIds, String name);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdInAndLinkedToPinGroupId(List<Long> operatingModeIds, Long linkedToPinGroupId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdAndLinkedToPinGroupIdIn(Long operatingModeId, List<Long> linkedToPinGroupIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdAndLinkedToPinGroupNotNull(Long operatingModeId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdInAndLinkedToPinGroupNotNull(List<Long> operatingModeId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinOperatingModeIdInAndLinkedToPinGroupName(List<Long> operatingModeIds, String name);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinDelayMs(Integer delayMs);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinDelayMs(Integer delayMs, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinIsAvailable(Boolean isAvailable);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinIsAvailable(Boolean isAvailable, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupId(Long linkedToPinGroupId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupId(Long linkedToPinGroupId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupIdIn(List<Long> linkedToPinGroupIds, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupIdIn(List<Long> linkedToPinGroupIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupIdAndTitle(Long linkedToPinGroupId, String title, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupIdAndTitle(Long linkedToPinGroupId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByLinkedToPinGroupIdAndTitle(Long linkedToPinGroupId, String title);
					
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupIdAndIconId(Long linkedToPinGroupId, Long iconId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupIdAndIconId(Long linkedToPinGroupId, Long iconId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByLinkedToPinGroupIdAndIconId(Long linkedToPinGroupId, Long iconId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupIdAndIconIdAndTitle(Long linkedToPinGroupId, Long iconId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByLinkedToPinGroupIdAndIconIdAndTitle(Long linkedToPinGroupId, Long iconId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupIdAndTypeId(Long linkedToPinGroupId, Long typeId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupIdAndTypeId(Long linkedToPinGroupId, Long typeId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByLinkedToPinGroupIdAndTypeId(Long linkedToPinGroupId, Long typeId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupIdAndTypeIdAndTitle(Long linkedToPinGroupId, Long typeId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByLinkedToPinGroupIdAndTypeIdAndTitle(Long linkedToPinGroupId, Long typeId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupIdAndLinkedToPinId(Long linkedToPinGroupId, Long linkedToPinId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupIdAndLinkedToPinId(Long linkedToPinGroupId, Long linkedToPinId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByLinkedToPinGroupIdAndLinkedToPinId(Long linkedToPinGroupId, Long linkedToPinId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupIdAndLinkedToPinIdAndTitle(Long linkedToPinGroupId, Long linkedToPinId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	UiButton findFirstByLinkedToPinGroupIdAndLinkedToPinIdAndTitle(Long linkedToPinGroupId, Long linkedToPinId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupName(String name);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupName(String name, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdIn(List<Long> typeIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupTypeId(Long typeId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupTypeId(Long typeId, Pageable pageable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdAndTitle(Long typeId, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndTitle(List<Long> typeIds, String title);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndIconId(List<Long> typeIds, Long iconId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdAndIconIdIn(Long typeId, List<Long> iconIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndTypeId(List<Long> typeIds, Long typeId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdAndTypeIdIn(Long typeId, List<Long> typeIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdAndTypeNotNull(Long typeId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndTypeNotNull(List<Long> typeId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndTypeName(List<Long> typeIds, String name);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndLinkedToPinId(List<Long> typeIds, Long linkedToPinId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdAndLinkedToPinIdIn(Long typeId, List<Long> linkedToPinIds);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdAndLinkedToPinNotNull(Long typeId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndLinkedToPinNotNull(List<Long> typeId);
	
	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndLinkedToPinName(List<Long> typeIds, String name);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndLinkedToPinBoardId(List<Long> typeIds, Long boardId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndLinkedToPinGpioId(List<Long> typeIds, Long gpioId);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndLinkedToPinDelayMs(List<Long> typeIds, Integer delayMs);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByLinkedToPinGroupTypeIdInAndLinkedToPinIsAvailable(List<Long> typeIds, Boolean isAvailable);

	@EntityGraph(attributePaths = { "icon", "type", "linkedToPin", "linkedToPin.piInstance", "linkedToPin.operatingMode", "linkedToPinGroup", "linkedToPinGroup.type", "buttonsPanel"})
	List<UiButton> findAllByButtonsPanelId(Long buttonsPanelId);
}
