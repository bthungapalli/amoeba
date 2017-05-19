package com.js.amoeba.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.js.amoeba.domain.User;



public class SecurityUser  extends User implements UserDetails  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 138086893486780306L;

	public SecurityUser(User user) {
		if (user != null) {
	
		
			this.setId(user.getId());
			this.setEmail(user.getEmail());
			this.setRole(user.getRole());
			this.setPassword(user.getPassword());
			this.setFirstName(user.getFirstName());
			this.setLastName(user.getLastName());
			this.setPhone(user.getPhone());
			//this.setUpdatedAt(user.getUpdatedAt());
			this.setCreatedAt(user.getCreatedAt());
			this.setConfirmed(user.isConfirmed());
			this.setVerification(user.isVerification());
			//this.setMailAccount(user.getMailAccount());
			this.setAddress(user.getAddress());
			this.setGender(user.getGender());
			this.setExpiryDate((user.getExpiryDate()));
		}
		}
	
	
	
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(
				verifyUserRole());
				
		authorities.add(authority);
		return authorities;
	}
	private String verifyUserRole() {
		switch (this.getRole()) {
		case 0:
			return "ROLE_USER";
		case 1:
			return "ROLE_CONSULTANT";
		case 2:
			return "ROLE_ADMIN";
		case 3 :
			return "ROLE_NGO";
		default:
			return "ROLE_INVALID";
		}
	}
	

	


	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}



	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}



	@Override
	public boolean isEnabled() {
		return true;
	}

}
