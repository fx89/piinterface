package com.desolatetimelines.piinterface.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.desolatetimelines.piinterface.model.IpAddressRange;

public interface IpAddressRangesRepository extends PagingAndSortingRepository<IpAddressRange, Long> {

	@Modifying
	@Query("delete from IpAddressRange x where x.id in ?1")
	void bulkDelete(List<Long> ids);

}
