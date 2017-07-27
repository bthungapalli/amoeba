package com.js.amoeba.config;

import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.js.amoeba.constants.AmoebaConstants;
import com.js.amoeba.domain.User;




@Component
@EnableAsync
public class MailUtil {

	@Value("${email.from}")
	private String from;

	@Value("${contextPath}")
	private String contextPath;

	@Inject
	private JavaMailSender javaMailSender;
	
	@Inject
	private SpringTemplateEngine templateEngine;


	@Async
	@Bean
	@Lazy
	// method to send a mail
	public Future<Void> sendMail(User user, String url) throws MessagingException {
		long startTime = System.currentTimeMillis();
		ClassLoader classLoader = getClass().getClassLoader();
		final Context ctx = new Context();
		// ctx.setVariable("name", StringUtils.capitalize(user.getFirstName()+"
		// "+user.getLastName()));
		ctx.setVariable("email", user.getUserName());
		ctx.setVariable("path", url);
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, 1, "utf-8");
		helper.setFrom(from);
		helper.setSubject(AmoebaConstants.REGISTRATION_CONFIRMATION_SUBJECT);
		helper.setTo(user.getEmail());
		helper.setText(templateEngine.process(AmoebaConstants.REGISTRATION_CONFIRMATION_TEMPLATE, ctx), true);
		/*
		 * try { InputStreamSource imageSource = new ByteArrayResource(
		 * IOUtils.toByteArray(classLoader
		 * .getResourceAsStream("mail/impact_Logo.png")));
		 * helper.addInline("impact_Logo.png", imageSource, "image/png"); }
		 * catch (IOException e) { e.printStackTrace(); }
		 */
		javaMailSender.send(mimeMessage);
		long endTime = System.currentTimeMillis();
		System.out.println("Total execution time for Sending Email: " + (endTime - startTime) + "ms");
		return new AsyncResult<Void>(null);
	}
	
	
	@Async
	@Bean
	@Lazy
	public Future<Void> forgetPasswordNotifyMail(User user, String newPassword) throws MessagingException {
		System.out.println(user.getEmail());
		long startTime = System.currentTimeMillis();
		final Context ctx = new Context();
		String email = user.getEmail();
		System.out.println(email);
		ctx.setVariable("email", email);
		ctx.setVariable("password", newPassword);
		ctx.setVariable("name", user.getFirstName()+ " " +user.getLastName());
		ctx.setVariable("path", contextPath);

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, 1, "utf-8");
		System.out.println(3);
		helper.setFrom(from);
		helper.setSubject(AmoebaConstants.PASSWORD_CHANGED);
		helper.setTo(email);
		helper.setText(templateEngine.process(AmoebaConstants.PASSWORD_CHANGE_TEMPLATE, ctx), true);

		javaMailSender.send(mimeMessage);

		long endTime = System.currentTimeMillis();
		System.out.println("Total execution time for Sending Email: " + (endTime - startTime) + "ms");
		return new AsyncResult<Void>(null);
	}
	@Async
	@Bean
	@Lazy
	
	public Future<Void> userFormSubMail(User user) throws MessagingException {
		
		System.out.println(user.getEmail());
		long startTime = System.currentTimeMillis();
		final Context ctx = new Context();
		String email = user.getEmail();
		System.out.println(email);
		ctx.setVariable("email", email);
		
		ctx.setVariable("name", user.getFirstName()+ " " +user.getLastName());
		ctx.setVariable("path", contextPath);

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, 1, "utf-8");
		System.out.println(3);
		helper.setFrom(from);
		helper.setSubject(AmoebaConstants.USER_FORM_SUB);
		helper.setTo(email);
		helper.setText(templateEngine.process("FormSubMail",ctx),true);

		

		javaMailSender.send(mimeMessage);

		long endTime = System.currentTimeMillis();
		System.out.println("Total execution time for Sending Email: " + (endTime - startTime) + "ms");
		return new AsyncResult<Void>(null);
		
		
	}


}
