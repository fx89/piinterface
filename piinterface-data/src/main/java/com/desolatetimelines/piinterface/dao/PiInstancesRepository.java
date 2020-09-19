package com.desolatetimelines.piinterface.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.desolatetimelines.piinterface.model.PiInstance;

public interface PiInstancesRepository extends PagingAndSortingRepository<PiInstance, Long> {

	@Modifying
	@Query("delete from PiInstance x where x.id in ?1")
	void bulkDelete(List<Long> ids);
	
	Optional<PiInstance> findOneByName(String name);
	List<PiInstance> findAllByNameStartingWith(String name);
	PiInstance findFirstByNameStartingWith(String name);

	List<PiInstance> findAllByNameAndLastRegisteredAddress(String name, String lastRegisteredAddress);

	List<PiInstance> findAllByLastRegisteredAddressIn(List<String> lastRegisteredAddresses);

	List<PiInstance> findAllByLastRegisteredAddress(String lastRegisteredAddress, Pageable pageable);

	List<PiInstance> findAllByLastRegisteredAddress(String lastRegisteredAddress);
	
	PiInstance findFirstByLastRegisteredAddress(String lastRegisteredAddress);

	List<PiInstance> findAllByLastRegisteredAddressAndName(String lastRegisteredAddress, String name);

}
