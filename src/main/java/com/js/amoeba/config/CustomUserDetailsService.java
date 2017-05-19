package com.js.amoeba.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.js.amoeba.domain.User;
import com.js.amoeba.exception.AmoebaDaoException;
import com.js.amoeba.service.UserService;



@Component
public class CustomUserDetailsService   implements UserDetailsService{
	
		@Autowired
		private UserService userService;

		
		@Override
		public UserDetails loadUserByUsername(String userEmailId)
				throws UsernameNotFoundException {
			User user = userService.getUserDetailsByUserName(userEmailId);
			if(user == null){
				throw new UsernameNotFoundException("UserName "+userEmailId+" not found", new AmoebaDaoException());
			}
			
			return new SecurityUser(user);
		}
}
