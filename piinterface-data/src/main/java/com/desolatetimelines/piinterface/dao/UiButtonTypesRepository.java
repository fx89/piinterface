package com.desolatetimelines.piinterface.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.desolatetimelines.piinterface.model.UiButtonType;

public interface UiButtonTypesRepository extends PagingAndSortingRepository<UiButtonType, Long> {

	@Modifying
	@Query("delete from UiButtonType x where x.id in ?1")
	void bulkDelete(List<Long> ids);
	
	Optional<UiButtonType> findOneByName(String name);
	List<UiButtonType> findAllByNameStartingWith(String name);
	UiButtonType findFirstByNameStartingWith(String name);

}
