package com.webessay.model;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Jobs.class)
public interface JobsRepository {
		
	@Query(value = "select * from jobs j where j.status = :status and customerid = :customerId", nativeQuery = true)
	List<Jobs> getCustomerProcessing(@Param("status") Integer jobstatus, @Param("customerId") Integer customerId);
	
	@Query(value = "select * from jobs j where j.status = :status and writerid = :writerId", nativeQuery = true)
	List<Jobs> getWriterProcessing(@Param("status") Integer jobstatus, @Param("writerId") Integer writerId);
}
