package com.js.amoeba.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.js.amoeba.service.ConsultantService;
import com.js.amoeba.service.FormService;
import com.js.amoeba.service.MessageService;
import com.js.amoeba.service.UserService;
import com.js.amoeba.utils.CalendarSync;


public class AppInitializer  extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.addListener(new SessionListener());
    	System.setProperty("net.sf.ehcache.skipUpdateCheck", "true");
    }
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return new Class<?>[] {  DataConfig.class, ViewResolver.class, MailConfig.class,ConsultantService.class,FormService.class,MessageService.class,
			UserService.class, CustomUserDetailsService.class,MailUtil.class, ThymeLeafConfig.class};

	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		return new Filter[] { characterEncodingFilter, new DelegatingFilterProxy("springSecurityFilterChain") };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	

}
