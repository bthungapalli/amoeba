package com.js.amoeba.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

public class ThymeLeafConfig {
	@Bean
	ClassLoaderTemplateResolver emailTemplateResolver() {
		final ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
		resolver.setPrefix("mail/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode("HTML5");
		resolver.setCharacterEncoding("UTF-8");
		resolver.setOrder(1);
		return resolver;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine() {
		final SpringTemplateEngine engine = new SpringTemplateEngine();
		final Set<ITemplateResolver> templateResolvers = new HashSet<ITemplateResolver>();
		templateResolvers.add(emailTemplateResolver());
		engine.setTemplateResolvers(templateResolvers);
		return engine;
	}

}
