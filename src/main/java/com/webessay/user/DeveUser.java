package com.webessay.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import com.webessay.config.Config;
import com.webessay.model.Userinfo;

@Component
public class DeveUser {

	@Autowired
	Config config;
	
	public int getUserID(){
		return config.getDeveloperid();
	}
	
	
	//TODO: role_admin has to change
	public RegisteredUser fakeUser(){
        return new RegisteredUser(config.getDeveloperemail(), 
        		config.getDeveloperPassword(),
        		true, true,
        		true, true,                 
        		AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_SUPER"), getUserID());
	}
	
	public Userinfo loadDeveloper(){
		return null;
	}
}
