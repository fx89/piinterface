package com.desolatetimelines.piinterface.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.desolatetimelines.piinterface.model.PinCalendarEntry;

public interface PinCalendarEntriesRepository extends PagingAndSortingRepository<PinCalendarEntry, Long> {
	@Modifying
	@Query("delete from PiInstance x where x.id in ?1")
	void bulkDelete(List<Long> ids);

	Optional<PinCalendarEntry> findOneByTitle(String name);
}
