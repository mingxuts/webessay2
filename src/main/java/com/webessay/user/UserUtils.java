package com.webessay.user;

import java.security.Principal;

import org.springframework.security.core.Authentication;


public class UserUtils {
	
	public static int getUserId(Principal principal){
		RegisteredUser user = (RegisteredUser)((Authentication) principal).getPrincipal();
		return user.getUserID();
	}
}
