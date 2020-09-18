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
	
	@EntityGraph(attributePaths = { "operatingMode"})
	Optional<Pin> findOneByName(String name);
	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByNameStartingWith(String name);
	@EntityGraph(attributePaths = { "operatingMode"})
	Pin findFirstByNameStartingWith(String name);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByNameAndBoardId(String name, Long boardId);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByNameAndGpioId(String name, Long gpioId);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByNameAndDelayMs(String name, Integer delayMs);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByBoardIdIn(List<Long> boardIds);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByBoardId(Long boardId, Pageable pageable);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByBoardId(Long boardId);
	
	@EntityGraph(attributePaths = { "operatingMode"})
	Pin findFirstByBoardId(Long boardId);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByBoardIdAndName(Long boardId, String name);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByBoardIdAndGpioId(Long boardId, Long gpioId);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByBoardIdAndDelayMs(Long boardId, Integer delayMs);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByGpioIdIn(List<Long> gpioIds);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByGpioId(Long gpioId, Pageable pageable);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByGpioId(Long gpioId);
	
	@EntityGraph(attributePaths = { "operatingMode"})
	Pin findFirstByGpioId(Long gpioId);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByGpioIdAndName(Long gpioId, String name);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByGpioIdAndBoardId(Long gpioId, Long boardId);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByGpioIdAndDelayMs(Long gpioId, Integer delayMs);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByOperatingModeId(Long operatingModeId, Pageable pageable);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByOperatingModeId(Long operatingModeId);
	
	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByOperatingModeIdIn(List<Long> operatingModeIds, Pageable pageable);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByOperatingModeIdIn(List<Long> operatingModeIds);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndName(Long operatingModeId, String name, Pageable pageable);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndName(Long operatingModeId, String name);

	@EntityGraph(attributePaths = { "operatingMode"})
	Pin findFirstByOperatingModeIdAndName(Long operatingModeId, String name);
					
	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndBoardId(Long operatingModeId, Long boardId, Pageable pageable);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndBoardId(Long operatingModeId, Long boardId);

	@EntityGraph(attributePaths = { "operatingMode"})
	Pin findFirstByOperatingModeIdAndBoardId(Long operatingModeId, Long boardId);
					
	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndGpioId(Long operatingModeId, Long gpioId, Pageable pageable);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndGpioId(Long operatingModeId, Long gpioId);

	@EntityGraph(attributePaths = { "operatingMode"})
	Pin findFirstByOperatingModeIdAndGpioId(Long operatingModeId, Long gpioId);
					
	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndDelayMs(Long operatingModeId, Integer delayMs, Pageable pageable);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByOperatingModeIdAndDelayMs(Long operatingModeId, Integer delayMs);

	@EntityGraph(attributePaths = { "operatingMode"})
	Pin findFirstByOperatingModeIdAndDelayMs(Long operatingModeId, Integer delayMs);
					
	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByOperatingModeName(String name);
	
	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByOperatingModeName(String name, Pageable pageable);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByDelayMsIn(List<Integer> delayMs);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByDelayMs(Integer delayMs, Pageable pageable);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByDelayMs(Integer delayMs);
	
	@EntityGraph(attributePaths = { "operatingMode"})
	Pin findFirstByDelayMs(Integer delayMs);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByDelayMsAndName(Integer delayMs, String name);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByDelayMsAndBoardId(Integer delayMs, Long boardId);

	@EntityGraph(attributePaths = { "operatingMode"})
	List<Pin> findAllByDelayMsAndGpioId(Integer delayMs, Long gpioId);

}
