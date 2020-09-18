package com.desolatetimelines.piinterface.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.desolatetimelines.piinterface.model.PinGroupType;

public interface PinGroupTypesRepository extends PagingAndSortingRepository<PinGroupType, Long> {

	@Modifying
	@Query("delete from PinGroupType x where x.id in ?1")
	void bulkDelete(List<Long> ids);
	
	Optional<PinGroupType> findOneByName(String name);
	List<PinGroupType> findAllByNameStartingWith(String name);
	PinGroupType findFirstByNameStartingWith(String name);

}
