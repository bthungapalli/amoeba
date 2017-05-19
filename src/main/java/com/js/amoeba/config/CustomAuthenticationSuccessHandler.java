package com.js.amoeba.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;



public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {
		SecurityUser authUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		HttpSession session = httpServletRequest.getSession();
		session.setAttribute("userInfo", authUser);
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		  
	}

}
