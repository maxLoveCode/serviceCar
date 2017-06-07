package serviceCar.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import pojo.User;
import serviceCar.service.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        User user = userService.findUserByUsername(name);
        if(user != null)
        {
        	if (user.getPassword().equals(password))
        	{
        		return new UsernamePasswordAuthenticationToken(name, password,authentication.getAuthorities());
        	}
        	else
        	{
        		throw new BadCredentialsException("密码错误");
        	}
        }
        else 
        	throw new BadCredentialsException("用户名错误");
                                                                                                                                                                                       
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}
}
