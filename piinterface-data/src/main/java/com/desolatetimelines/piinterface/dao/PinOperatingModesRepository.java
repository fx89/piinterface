package com.desolatetimelines.piinterface.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.desolatetimelines.piinterface.model.PinOperatingMode;

public interface PinOperatingModesRepository extends PagingAndSortingRepository<PinOperatingMode, Long> {

	@Modifying
	@Query("delete from PinOperatingMode x where x.id in ?1")
	void bulkDelete(List<Long> ids);
	
	Optional<PinOperatingMode> findOneByName(String name);
	List<PinOperatingMode> findAllByNameStartingWith(String name);
	PinOperatingMode findFirstByNameStartingWith(String name);

}
