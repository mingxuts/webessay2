package com.webessay.controller;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.webessay.db.specification.UserinfoSpecifications;
import com.webessay.model.Userinfo;
import com.webessay.model.UserinfoRepository;

@RequestMapping("/signup/**")
@Controller
public class Signup {
	
	private static Logger log = Logger.getLogger(Signup.class);
	
	@Autowired
	private UserinfoRepository repo;

    @RequestMapping(method = RequestMethod.POST, value = "index")
	public String post(Model uiModel, @RequestParam("loginemail") String email,
			@RequestParam("loginpassword") String password,
			@RequestParam("contactType") String contactType,
			@RequestParam("contactnumber") String contactNumber,
			@RequestParam("firstname") String firstName,
			@RequestParam("lastname") String lastName){
    	
    	
		//We can't let email duplicated in our db
		if (repo
				.count(UserinfoSpecifications.emailEqual(email) ) != 0) {
			log.debug("Email has existed");
			return "signup/index";
		} else {    
			Userinfo userinfo = new Userinfo();
			String country = "";
			String city    = "";

			boolean passwordNonExpired = true;
			userinfo.setEmail(email);
			userinfo.setLoginPassword(password);
			userinfo.setGroupName("ROLE_USER");
			userinfo.setFirstName(firstName);
			userinfo.setLastName(lastName);
			userinfo.setContactType(contactType);
			userinfo.setContactId(contactNumber);
			userinfo.setCountry(country);
			userinfo.setCity(city);
			userinfo.setHasVerified(false);
			userinfo.setPasswordNonExpired(passwordNonExpired);
			repo.save(userinfo);
			
			uiModel.addAttribute("signup_success", "true");
			return "redirect:/login";
		}
    	
    }

    @RequestMapping
    public String index() {
        return "signup/index";
    }
}
