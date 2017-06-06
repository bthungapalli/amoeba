package com.js.amoeba.config;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.js.amoeba.domain.User;
import com.js.amoeba.service.UserService;




@Component
public class CustomUserDetailsService implements UserDetailsService

{
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		System.out.println(email);
		User user = userService.getUserDetailsByUserName(email);
		if(user == null){
			throw new UsernameNotFoundException("UserName "+email+" not found");
		}
		
		return new SecurityUser(user);
	}
}