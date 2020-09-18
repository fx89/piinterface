package com.desolatetimelines.piinterface.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.desolatetimelines.piinterface.model.PinGroup;

public interface PinGroupsRepository extends PagingAndSortingRepository<PinGroup, Long> {

	@Modifying
	@Query("delete from PinGroup x where x.id in ?1")
	void bulkDelete(List<Long> ids);
	
	@EntityGraph(attributePaths = { "type"})
	Optional<PinGroup> findOneByName(String name);
	@EntityGraph(attributePaths = { "type"})
	List<PinGroup> findAllByNameStartingWith(String name);
	@EntityGraph(attributePaths = { "type"})
	PinGroup findFirstByNameStartingWith(String name);

	@EntityGraph(attributePaths = { "type"})
	List<PinGroup> findAllByTypeId(Long typeId, Pageable pageable);

	@EntityGraph(attributePaths = { "type"})
	List<PinGroup> findAllByTypeId(Long typeId);
	
	@EntityGraph(attributePaths = { "type"})
	List<PinGroup> findAllByTypeIdIn(List<Long> typeIds, Pageable pageable);

	@EntityGraph(attributePaths = { "type"})
	List<PinGroup> findAllByTypeIdIn(List<Long> typeIds);

	@EntityGraph(attributePaths = { "type"})
	List<PinGroup> findAllByTypeIdAndName(Long typeId, String name, Pageable pageable);

	@EntityGraph(attributePaths = { "type"})
	List<PinGroup> findAllByTypeIdAndName(Long typeId, String name);

	@EntityGraph(attributePaths = { "type"})
	PinGroup findFirstByTypeIdAndName(Long typeId, String name);
					
	@EntityGraph(attributePaths = { "type"})
	List<PinGroup> findAllByTypeName(String name);
	
	@EntityGraph(attributePaths = { "type"})
	List<PinGroup> findAllByTypeName(String name, Pageable pageable);

}
