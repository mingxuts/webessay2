package com.webessay.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.webessay.config.Config;
import com.webessay.model.Userinfo;
import com.webessay.model.UserinfoRepository;

public class RegisteredUser extends User {
	
	@Autowired
	Config config;
	
	@Autowired
	DeveUser deveuser;
	
	@Autowired
	SuperUser superuser;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int userID;

	public int getUserID() {
		return userID;
	}
	
	public RegisteredUser(String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired,
            boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities, int userID)	{
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userID = userID;
	}
	
	public Userinfo loadUserinfo(UserinfoRepository userinfoRepo){
		if (getUserID() == config.getSuperid()){
			return superuser.loadSuper();
		} else if (getUserID() == config.getDeveloperid()){
			return deveuser.loadDeveloper();
		} else {
			return userinfoRepo.findOne(this.userID);
		}
	}
}
