package com.desolatetimelines.piinterface.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.desolatetimelines.piinterface.model.Icon;

public interface IconsRepository extends PagingAndSortingRepository<Icon, Long> {

	@Modifying
	@Query("delete from Icon x where x.id in ?1")
	void bulkDelete(List<Long> ids);
	
	Optional<Icon> findOneByName(String name);
	List<Icon> findAllByNameStartingWith(String name);
	Icon findFirstByNameStartingWith(String name);

}
