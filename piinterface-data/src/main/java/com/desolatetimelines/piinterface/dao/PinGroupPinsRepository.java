package com.desolatetimelines.piinterface.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.desolatetimelines.piinterface.model.PinGroupPin;

public interface PinGroupPinsRepository extends PagingAndSortingRepository<PinGroupPin, Long> {

	@Modifying
	@Query("delete from PinGroupPin x where x.id in ?1")
	void bulkDelete(List<Long> ids);
	
	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupId(Long pinGroupId, Pageable pageable);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupId(Long pinGroupId);
	
	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupIdIn(List<Long> pinGroupIds, Pageable pageable);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupIdIn(List<Long> pinGroupIds);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupIdAndPinId(Long pinGroupId, Long pinId, Pageable pageable);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupIdAndPinId(Long pinGroupId, Long pinId);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	PinGroupPin findFirstByPinGroupIdAndPinId(Long pinGroupId, Long pinId);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupIdAndPinIdAndOrder(Long pinGroupId, Long pinId, Integer order);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	PinGroupPin findFirstByPinGroupIdAndPinIdAndOrder(Long pinGroupId, Long pinId, Integer order);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupIdAndPinIdAndStatesCount(Long pinGroupId, Long pinId, Integer statesCount);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	PinGroupPin findFirstByPinGroupIdAndPinIdAndStatesCount(Long pinGroupId, Long pinId, Integer statesCount);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupIdAndOrder(Long pinGroupId, Integer order, Pageable pageable);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupIdAndOrder(Long pinGroupId, Integer order);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	PinGroupPin findFirstByPinGroupIdAndOrder(Long pinGroupId, Integer order);
					
	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupIdAndStatesCount(Long pinGroupId, Integer statesCount, Pageable pageable);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupIdAndStatesCount(Long pinGroupId, Integer statesCount);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	PinGroupPin findFirstByPinGroupIdAndStatesCount(Long pinGroupId, Integer statesCount);
					
	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupName(String name);
	
	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupName(String name, Pageable pageable);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupTypeIdIn(List<Long> typeIds);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupTypeId(Long typeId);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupTypeId(Long typeId, Pageable pageable);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupTypeIdInAndPinId(List<Long> typeIds, Long pinId);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupTypeIdAndPinIdIn(Long typeId, List<Long> pinIds);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupTypeIdAndPinNotNull(Long typeId);
	
	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupTypeIdInAndPinNotNull(List<Long> typeId);
	
	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupTypeIdInAndPinName(List<Long> typeIds, String name);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupTypeIdInAndPinBoardId(List<Long> typeIds, Long boardId);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupTypeIdInAndPinGpioId(List<Long> typeIds, Long gpioId);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupTypeIdInAndPinDelayMs(List<Long> typeIds, Integer delayMs);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupTypeIdInAndPinIsAvailable(List<Long> typeIds, Boolean isAvailable);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupTypeIdAndOrder(Long typeId, Integer order);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupTypeIdInAndOrder(List<Long> typeIds, Integer order);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupTypeIdAndStatesCount(Long typeId, Integer statesCount);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGroupTypeIdInAndStatesCount(List<Long> typeIds, Integer statesCount);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinId(Long pinId, Pageable pageable);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinId(Long pinId);
	
	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinIdIn(List<Long> pinIds, Pageable pageable);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinIdIn(List<Long> pinIds);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinIdAndPinGroupId(Long pinId, Long pinGroupId, Pageable pageable);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinIdAndPinGroupId(Long pinId, Long pinGroupId);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	PinGroupPin findFirstByPinIdAndPinGroupId(Long pinId, Long pinGroupId);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinIdAndPinGroupIdAndOrder(Long pinId, Long pinGroupId, Integer order);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	PinGroupPin findFirstByPinIdAndPinGroupIdAndOrder(Long pinId, Long pinGroupId, Integer order);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinIdAndPinGroupIdAndStatesCount(Long pinId, Long pinGroupId, Integer statesCount);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	PinGroupPin findFirstByPinIdAndPinGroupIdAndStatesCount(Long pinId, Long pinGroupId, Integer statesCount);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinIdAndOrder(Long pinId, Integer order, Pageable pageable);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinIdAndOrder(Long pinId, Integer order);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	PinGroupPin findFirstByPinIdAndOrder(Long pinId, Integer order);
					
	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinIdAndStatesCount(Long pinId, Integer statesCount, Pageable pageable);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinIdAndStatesCount(Long pinId, Integer statesCount);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	PinGroupPin findFirstByPinIdAndStatesCount(Long pinId, Integer statesCount);
					
	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinPiInstanceIdIn(List<Long> piInstanceIds);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinPiInstanceId(Long piInstanceId);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinPiInstanceId(Long piInstanceId, Pageable pageable);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinPiInstanceIdInAndPinGroupId(List<Long> piInstanceIds, Long pinGroupId);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinPiInstanceIdAndPinGroupIdIn(Long piInstanceId, List<Long> pinGroupIds);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinPiInstanceIdAndPinGroupNotNull(Long piInstanceId);
	
	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinPiInstanceIdInAndPinGroupNotNull(List<Long> piInstanceId);
	
	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinPiInstanceIdInAndPinGroupName(List<Long> piInstanceIds, String name);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinPiInstanceIdAndOrder(Long piInstanceId, Integer order);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinPiInstanceIdInAndOrder(List<Long> piInstanceIds, Integer order);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinPiInstanceIdAndStatesCount(Long piInstanceId, Integer statesCount);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinPiInstanceIdInAndStatesCount(List<Long> piInstanceIds, Integer statesCount);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinName(String name);
	
	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinName(String name, Pageable pageable);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinBoardId(Long boardId);
	
	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinBoardId(Long boardId, Pageable pageable);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGpioId(Long gpioId);
	
	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinGpioId(Long gpioId, Pageable pageable);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinOperatingModeIdIn(List<Long> operatingModeIds);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinOperatingModeId(Long operatingModeId);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinOperatingModeId(Long operatingModeId, Pageable pageable);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinOperatingModeIdInAndPinGroupId(List<Long> operatingModeIds, Long pinGroupId);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinOperatingModeIdAndPinGroupIdIn(Long operatingModeId, List<Long> pinGroupIds);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinOperatingModeIdAndPinGroupNotNull(Long operatingModeId);
	
	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinOperatingModeIdInAndPinGroupNotNull(List<Long> operatingModeId);
	
	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinOperatingModeIdInAndPinGroupName(List<Long> operatingModeIds, String name);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinOperatingModeIdAndOrder(Long operatingModeId, Integer order);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinOperatingModeIdInAndOrder(List<Long> operatingModeIds, Integer order);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinOperatingModeIdAndStatesCount(Long operatingModeId, Integer statesCount);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinOperatingModeIdInAndStatesCount(List<Long> operatingModeIds, Integer statesCount);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinDelayMs(Integer delayMs);
	
	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinDelayMs(Integer delayMs, Pageable pageable);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinIsAvailable(Boolean isAvailable);
	
	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByPinIsAvailable(Boolean isAvailable, Pageable pageable);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByOrderIn(List<Integer> orders);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByOrder(Integer order, Pageable pageable);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByOrder(Integer order);
	
	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	PinGroupPin findFirstByOrder(Integer order);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByOrderAndStatesCount(Integer order, Integer statesCount);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByStatesCountIn(List<Integer> statesCounts);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByStatesCount(Integer statesCount, Pageable pageable);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByStatesCount(Integer statesCount);
	
	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	PinGroupPin findFirstByStatesCount(Integer statesCount);

	@EntityGraph(attributePaths = { "pinGroup", "pinGroup.type", "pin", "pin.piInstance", "pin.operatingMode"})
	List<PinGroupPin> findAllByStatesCountAndOrder(Integer statesCount, Integer order);

}
