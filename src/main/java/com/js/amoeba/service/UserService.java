package com.js.amoeba.service;


import java.security.SecureRandom;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.amoeba.config.MailUtil;
import com.js.amoeba.config.SecurityUser;
import com.js.amoeba.constants.AmoebaConstants;
import com.js.amoeba.dao.UserDao;
import com.js.amoeba.domain.Consultant;
import com.js.amoeba.domain.User;
import com.js.amoeba.domain.VerifyToken;
import com.js.amoeba.exception.AmoebaException;




@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	private MailUtil mailUtils;
	
	
	private static SecureRandom rnd = new SecureRandom();

	@Transactional
	public void saveUser(User user) throws Exception {
		System.out.println("2");
		String password = encodePassword(user.getPassword());
		user.setPassword(password);
		System.out.println(user.getPassword());
		userDao.saveUser(user);
		System.out.println("3");

	}
	@Transactional
	public User getUserDetailsByUserName(String userName){
		return userDao.getUserDetailsByUserName(userName);
	}
	
	private String encodePassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}
	
	public void activateUser(String userName) throws AmoebaException {
		userDao.activateUser(userName);
		
	}
	public void deActivateUser(String userName) throws AmoebaException{
		userDao.deActivateUser(userName);
		
	}
	public void updateToken(VerifyToken verifyToken) throws AmoebaException {
		userDao.updateToken(verifyToken);
	}
	public VerifyToken verifyToken(String token) throws AmoebaException {
		return userDao.verifyToken(token);
	}
	public void updateConfirmation(Boolean confirmed, Boolean verified, String token) throws AmoebaException {
		userDao.updateConfirmation(confirmed,verified,token) ;
		
	}
	
	public int validateEmail(String emailId) {
		return userDao.validateEmail(emailId);
	}
	
	@Transactional
	public void updateUser(User user){
		userDao.updateUser(user);
	}
	
	public List<User> fetchAllUsers() throws AmoebaException {
		return userDao.fetchAllUsers();
	}
	public User fetchUserById(Integer userId) throws AmoebaException {
		return userDao.fetchUserById(userId);
	}
	
	public String getNewPassword(String emailId) throws MessagingException, AmoebaException {
		User user =userDao.getUserDetailsByUserName(emailId);
		if(user !=null){
		String newPassword = generateRandomPassword();
		updatePassword(user, newPassword);
		return "Registed";
		}
		else{
			return "Not Registred";
		}
		
	}
	/**
	 * @param user
	 * @param newPassword
	 * @throws MessagingException 
	 */
	public void updatePassword(User user, String newPassword) throws AmoebaException, MessagingException {
		user.setPassword(encodePassword(newPassword));
		userDao.updatePassword(user);
		
	}
		
	private String generateRandomPassword() {
		StringBuilder sb = new StringBuilder(AmoebaConstants.PASSWORD_SIZE);
		for (int i = 0; i < AmoebaConstants.PASSWORD_SIZE; i++)
			sb.append(AmoebaConstants.PASSWORD_AB.charAt(rnd
					.nextInt(AmoebaConstants.PASSWORD_AB.length())));
		return sb.toString();
	}
	@Transactional
	public void updateUser1(SecurityUser securityUser, Consultant consultant) {
		userDao.updateUser1(securityUser,consultant);
	}


}
