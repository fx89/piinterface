package com.desolatetimelines.piinterface.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.desolatetimelines.piinterface.model.Pin;

public interface PinsRepository extends PagingAndSortingRepository<Pin, Long> {

	@Modifying
	@Query("delete from Pin x where x.id in ?1")
	void bulkDelete(List<Long> ids);
	
	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceId(Long piInstanceId, Pageable pageable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceId(Long piInstanceId);
	
	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceIdIn(List<Long> piInstanceIds, Pageable pageable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceIdIn(List<Long> piInstanceIds);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceIdAndName(Long piInstanceId, String name, Pageable pageable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceIdAndName(Long piInstanceId, String name);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByPiInstanceIdAndName(Long piInstanceId, String name);
					
	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceIdAndBoardId(Long piInstanceId, Long boardId, Pageable pageable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceIdAndBoardId(Long piInstanceId, Long boardId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByPiInstanceIdAndBoardId(Long piInstanceId, Long boardId);
					
	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceIdAndGpioId(Long piInstanceId, Long gpioId, Pageable pageable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceIdAndGpioId(Long piInstanceId, Long gpioId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByPiInstanceIdAndGpioId(Long piInstanceId, Long gpioId);
					
	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceIdAndOperatingModeId(Long piInstanceId, Long operatingModeId, Pageable pageable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceIdAndOperatingModeId(Long piInstanceId, Long operatingModeId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByPiInstanceIdAndOperatingModeId(Long piInstanceId, Long operatingModeId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceIdAndOperatingModeIdAndName(Long piInstanceId, Long operatingModeId, String name);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByPiInstanceIdAndOperatingModeIdAndName(Long piInstanceId, Long operatingModeId, String name);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceIdAndOperatingModeIdAndBoardId(Long piInstanceId, Long operatingModeId, Long boardId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByPiInstanceIdAndOperatingModeIdAndBoardId(Long piInstanceId, Long operatingModeId, Long boardId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceIdAndOperatingModeIdAndGpioId(Long piInstanceId, Long operatingModeId, Long gpioId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByPiInstanceIdAndOperatingModeIdAndGpioId(Long piInstanceId, Long operatingModeId, Long gpioId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceIdAndOperatingModeIdAndDelayMs(Long piInstanceId, Long operatingModeId, Integer delayMs);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByPiInstanceIdAndOperatingModeIdAndDelayMs(Long piInstanceId, Long operatingModeId, Integer delayMs);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceIdAndOperatingModeIdAndIsAvailable(Long piInstanceId, Long operatingModeId, Boolean isAvailable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByPiInstanceIdAndOperatingModeIdAndIsAvailable(Long piInstanceId, Long operatingModeId, Boolean isAvailable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceIdAndDelayMs(Long piInstanceId, Integer delayMs, Pageable pageable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceIdAndDelayMs(Long piInstanceId, Integer delayMs);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByPiInstanceIdAndDelayMs(Long piInstanceId, Integer delayMs);
					
	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceIdAndIsAvailable(Long piInstanceId, Boolean isAvailable, Pageable pageable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceIdAndIsAvailable(Long piInstanceId, Boolean isAvailable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByPiInstanceIdAndIsAvailable(Long piInstanceId, Boolean isAvailable);
					
	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceName(String name);
	
	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceName(String name, Pageable pageable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceLastRegisteredAddress(String lastRegisteredAddress);
	
	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByPiInstanceLastRegisteredAddress(String lastRegisteredAddress, Pageable pageable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Optional<Pin> findOneByName(String name);
	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByNameStartingWith(String name);
	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByNameStartingWith(String name);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByNameAndBoardId(String name, Long boardId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByNameAndGpioId(String name, Long gpioId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByNameAndDelayMs(String name, Integer delayMs);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByNameAndIsAvailable(String name, Boolean isAvailable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByBoardIdIn(List<Long> boardIds);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByBoardId(Long boardId, Pageable pageable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByBoardId(Long boardId);
	
	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByBoardId(Long boardId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByBoardIdAndName(Long boardId, String name);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByBoardIdAndGpioId(Long boardId, Long gpioId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByBoardIdAndDelayMs(Long boardId, Integer delayMs);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByBoardIdAndIsAvailable(Long boardId, Boolean isAvailable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByGpioIdIn(List<Long> gpioIds);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByGpioId(Long gpioId, Pageable pageable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByGpioId(Long gpioId);
	
	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByGpioId(Long gpioId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByGpioIdAndName(Long gpioId, String name);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByGpioIdAndBoardId(Long gpioId, Long boardId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByGpioIdAndDelayMs(Long gpioId, Integer delayMs);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByGpioIdAndIsAvailable(Long gpioId, Boolean isAvailable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByOperatingModeId(Long operatingModeId, Pageable pageable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByOperatingModeId(Long operatingModeId);
	
	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByOperatingModeIdIn(List<Long> operatingModeIds, Pageable pageable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByOperatingModeIdIn(List<Long> operatingModeIds);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndPiInstanceId(Long operatingModeId, Long piInstanceId, Pageable pageable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndPiInstanceId(Long operatingModeId, Long piInstanceId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByOperatingModeIdAndPiInstanceId(Long operatingModeId, Long piInstanceId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndPiInstanceIdAndName(Long operatingModeId, Long piInstanceId, String name);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByOperatingModeIdAndPiInstanceIdAndName(Long operatingModeId, Long piInstanceId, String name);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndPiInstanceIdAndBoardId(Long operatingModeId, Long piInstanceId, Long boardId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByOperatingModeIdAndPiInstanceIdAndBoardId(Long operatingModeId, Long piInstanceId, Long boardId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndPiInstanceIdAndGpioId(Long operatingModeId, Long piInstanceId, Long gpioId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByOperatingModeIdAndPiInstanceIdAndGpioId(Long operatingModeId, Long piInstanceId, Long gpioId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndPiInstanceIdAndDelayMs(Long operatingModeId, Long piInstanceId, Integer delayMs);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByOperatingModeIdAndPiInstanceIdAndDelayMs(Long operatingModeId, Long piInstanceId, Integer delayMs);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndPiInstanceIdAndIsAvailable(Long operatingModeId, Long piInstanceId, Boolean isAvailable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByOperatingModeIdAndPiInstanceIdAndIsAvailable(Long operatingModeId, Long piInstanceId, Boolean isAvailable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndName(Long operatingModeId, String name, Pageable pageable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndName(Long operatingModeId, String name);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByOperatingModeIdAndName(Long operatingModeId, String name);
					
	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndBoardId(Long operatingModeId, Long boardId, Pageable pageable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndBoardId(Long operatingModeId, Long boardId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByOperatingModeIdAndBoardId(Long operatingModeId, Long boardId);
					
	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndGpioId(Long operatingModeId, Long gpioId, Pageable pageable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndGpioId(Long operatingModeId, Long gpioId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByOperatingModeIdAndGpioId(Long operatingModeId, Long gpioId);
					
	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndDelayMs(Long operatingModeId, Integer delayMs, Pageable pageable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndDelayMs(Long operatingModeId, Integer delayMs);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByOperatingModeIdAndDelayMs(Long operatingModeId, Integer delayMs);
					
	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndIsAvailable(Long operatingModeId, Boolean isAvailable, Pageable pageable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndIsAvailable(Long operatingModeId, Boolean isAvailable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByOperatingModeIdAndIsAvailable(Long operatingModeId, Boolean isAvailable);
					
	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByOperatingModeName(String name);
	
	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByOperatingModeName(String name, Pageable pageable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByDelayMsIn(List<Integer> delayMs);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByDelayMs(Integer delayMs, Pageable pageable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByDelayMs(Integer delayMs);
	
	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByDelayMs(Integer delayMs);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByDelayMsAndName(Integer delayMs, String name);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByDelayMsAndBoardId(Integer delayMs, Long boardId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByDelayMsAndGpioId(Integer delayMs, Long gpioId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByDelayMsAndIsAvailable(Integer delayMs, Boolean isAvailable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByIsAvailableIn(List<Boolean> isAvailables);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByIsAvailable(Boolean isAvailable, Pageable pageable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByIsAvailable(Boolean isAvailable);
	
	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	Pin findFirstByIsAvailable(Boolean isAvailable);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByIsAvailableAndName(Boolean isAvailable, String name);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByIsAvailableAndBoardId(Boolean isAvailable, Long boardId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByIsAvailableAndGpioId(Boolean isAvailable, Long gpioId);

	@EntityGraph(attributePaths = { "piInstance", "operatingMode"})
	List<Pin> findAllByIsAvailableAndDelayMs(Boolean isAvailable, Integer delayMs);

}
