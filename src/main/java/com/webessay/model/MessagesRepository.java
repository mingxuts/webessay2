package com.webessay.model;
import java.util.List;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Messages.class)
public interface MessagesRepository {
	
	List<Messages> findByHasAdudit(Boolean hasAdudit);
}
