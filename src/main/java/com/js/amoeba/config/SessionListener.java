package com.js.amoeba.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener  implements HttpSessionListener  {

	private final static int SESSION_TIMEOUT_VALUE = 15*60*60;
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		se.getSession().setMaxInactiveInterval(SESSION_TIMEOUT_VALUE);

	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("Session timeOut");

	}
}
