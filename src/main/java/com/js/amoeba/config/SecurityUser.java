package com.js.amoeba.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.js.amoeba.domain.User;



public class SecurityUser extends User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6012656848373743308L;

	public SecurityUser(User user) {
		
		if(user !=null){
			this.setUserId(user.getUserId());
			this.setCreatedAt(user.getCreatedAt());
			this.setEmail(user.getEmail());
			this.setFirstName(user.getFirstName());
			this.setUserId(user.getUserId());
			this.setLastName(user.getLastName());
			this.setPassword(user.getPassword());
			this.setRole(user.getRole());
			this.setGender(user.getGender());
			this.setNumber(user.getNumber());
			this.setAddress(user.getAddress());
			this.setC_id(user.getC_id());
			this.setConfirmed(user.isConfirmed());
			this.setVerification(user.isVerification());
			
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
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
			return "ROLE_CONSULTENT";
		case 2 :
			return "ROLE_ADMIN";
		default:
			return "ROLE_INVALID";
		}
	}

	@Override
	public String getPassword() {
		return super.getPassword();
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

	@Override
	public String getUsername() {
		return super.getEmail();
	}

	

	





}
