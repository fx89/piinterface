package com.desolatetimelines.piinterface.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.desolatetimelines.piinterface.model.UiButtonsPanel;

public interface UiButtonsPanelsRepository extends PagingAndSortingRepository<UiButtonsPanel, Long> {

	@Modifying
	@Query("delete from UiButtonsPanel x where x.id in ?1")
	void bulkDelete(List<Long> ids);

}
