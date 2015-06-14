package com.webessay.model;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Messages.class)
public interface MessagesRepository {
	
	List<Messages> findByHasAdudit(Boolean hasAdudit);
	
	@Query(value = "select * from messages where HasRead = false and HasAdudit = true and (FromUser=:userid or ToUser=:userid)", nativeQuery= true)
	List<Messages> findUnreadMessages(@Param("userid") Integer id);
	
	@Query(value = "select * from messages where HasRead = true and HasAdudit = true and (FromUser=:userid or ToUser=:userid)", nativeQuery= true)
	List<Messages> findReadMessages(@Param("userid") Integer id);
}
