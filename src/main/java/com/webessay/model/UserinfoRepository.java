package com.webessay.model;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Userinfo.class)
public interface UserinfoRepository {
	
	Userinfo findByEmail(String email);
}
