package com.webessay.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.webessay.config.Config;
import com.webessay.model.Userinfo;
import com.webessay.model.UserinfoRepository;
import com.webessay.user.logic.CustomerProfile;
import com.webessay.user.logic.WriterProfile;

public class CustomerUserDetail implements UserDetailsService {
	
	@Autowired
	UserinfoRepository userinfoRepo;
	
	@Autowired
	private DeveUser deveuser;
	
	@Autowired
	private SuperUser superuser;
	
	@Autowired
	private Config config;
	
	@Value("${privateuser.emailsuffix}")
	private String emailsuffix;
	
	@Value("${super.username}")
	private String superusername;
	
	@Value("${developer.username}")
	private String developerusername;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		System.out.println("logging...");
		
		class throwexception{
			public void usernotfound(){
				throw new UsernameNotFoundException("User Not Found");
			}
		}
		RegisteredUser ret = null;
		
		if (username.endsWith(emailsuffix)){
			if (username.equalsIgnoreCase(developerusername + emailsuffix)){
				ret = deveuser.fakeUser();
			} else if (username.equalsIgnoreCase(superusername + emailsuffix)){
				ret = superuser.fakeUser();
			} else {
				new throwexception().usernotfound();
			}
		} else {
			System.out.println("finding user :" + username);
			Userinfo loggedOne = userinfoRepo.findByEmail(username);
			if (loggedOne == null ){
				new throwexception().usernotfound();
			}
			ret = mapUser(loggedOne);
		}
		return ret;
	}
	
	private RegisteredUser mapUser(Userinfo userinfo){
		System.out.println("password: " + userinfo.getLoginPassword());
		String email = userinfo.getEmail();
		String password = userinfo.getLoginPassword();
		boolean enabled = userinfo.getHasVerified();
		boolean accountNonExpired = userinfo.getPasswordNonExpired();
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        RegisteredUser user = new RegisteredUser(email,
                password.toLowerCase(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                AuthorityUtils
				.commaSeparatedStringToAuthorityList(userinfo.getGroupName()),
				userinfo.getId()); 
        System.out.println(user.getAuthorities().toArray()[0].toString());
        if (user.getAuthorities().toArray()[0].toString().startsWith("ROLE_USER")){
        	System.out.println("bingo");
        	user.setProfile(new CustomerProfile(user));
        }
        if (user.getAuthorities().toArray()[0].toString().startsWith("ROLE_WRITER")){
        	user.setProfile(new WriterProfile(user));
        }
        return user;		
	}

}
