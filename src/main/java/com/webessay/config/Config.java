package com.webessay.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {
	
	@Value("${super.userid}")
	int superid;
	
	@Value("${super.password}")
	String superpassword;
	
	@Value("${super.email}")
	String superemail;
	
	@Value("${developer.userid}")
	int developerUserid;

	@Value("${developer.password}")
	String developerpassword;
	
	@Value("${developer.email}")
	String developeremail;
	
	public int getSuperid(){
		return superid;
	}
	
	public String getSuperPassword(){
		return superpassword;
	}
	
	public int getDeveloperid(){
		return developerUserid;
	}
	
	public String getDeveloperPassword(){
		return developerpassword;
	}

	public String getSuperemail() {
		return superemail;
	}

	public String getDeveloperemail() {
		return developeremail;
	}	
}
