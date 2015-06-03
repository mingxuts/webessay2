package com.webessay.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import com.webessay.config.Config;
import com.webessay.model.Userinfo;

@Component
public class SuperUser {

	@Autowired
	Config config;
	
	public int getUserID(){
		return config.getSuperid();
	}
	
	//TODO: role_admin has to change
	public RegisteredUser fakeUser(){
        return new RegisteredUser(config.getSuperemail(), 
        		config.getSuperPassword(),
        		true, true,
        		true, true,                 
        		AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_ADMIN"), getUserID());
	}
	
	public Userinfo loadSuper(){
		return null;
	}	
}
