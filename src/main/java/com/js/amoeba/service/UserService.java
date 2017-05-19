package com.js.amoeba.service;

import java.security.SecureRandom;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.amoeba.config.MailUtil;
import com.js.amoeba.constants.AmoebaConstants;
import com.js.amoeba.dao.UserDao;
import com.js.amoeba.domain.User;
import com.js.amoeba.domain.UserDetails;
import com.js.amoeba.domain.VerifyToken;
import com.js.amoeba.exception.AmoebaDaoException;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	private static SecureRandom rnd = new SecureRandom();
	
	@Autowired
	private MailUtil mailUtils;

	@Transactional
	public void saveUser(User user) {
		System.out.println(2);
		String password = encodePassword(user.getPassword());
		user.setPassword(password);
		userDao.saveUser(user);
		System.out.println(3);

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
	
	public int validateEmail(String emailId) {
		System.out.println("mail");
		return userDao.validateEmail(emailId);
		
	}
	
	

@Transactional
	public void updateUser(User user){
		userDao.updateUser(user);
		
	}
public void activateUser(String userName) throws AmoebaDaoException {
	userDao.activateUser(userName);
	
}
public void deActivateUser(String userName) throws AmoebaDaoException{
	userDao.deActivateUser(userName);
	
}
public void updateToken(VerifyToken verifyToken) throws AmoebaDaoException {
	System.out.println("mail1");
	userDao.updateToken(verifyToken);
}
public VerifyToken verifyToken(String token) throws AmoebaDaoException {
	return userDao.verifyToken(token);
}
public void updateConfirmation(Boolean confirmed, Boolean verified, String token) throws AmoebaDaoException {
	userDao.updateConfirmation(confirmed,verified,token) ;
	
}
public List<User> fetchAllUsers() throws AmoebaDaoException {
	return userDao.fetchAllUsers();
}
public UserDetails fetchUserById(Integer userId) throws AmoebaDaoException {
	return userDao.fetchUserById(userId);
}

public String getNewPassword(String emailId) throws MessagingException, AmoebaDaoException {
	User user =userDao.getUserDetailsByUserName(emailId);
	if(user !=null){
	String newPassword = generateRandomPassword();
	//updatePassword(user, newPassword);
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
public void updatePassword(User user, String newPassword) throws AmoebaDaoException, MessagingException {
	user.setPassword(encodePassword(newPassword));
	userDao.updatePassword(user);
	mailUtils.forgetPasswordNotifyMail(user,newPassword);
}
	
private String generateRandomPassword() {
	StringBuilder sb = new StringBuilder(AmoebaConstants.PASSWORD_SIZE);
	for (int i = 0; i < AmoebaConstants.PASSWORD_SIZE; i++)
		sb.append(AmoebaConstants.PASSWORD_AB.charAt(rnd
				.nextInt(AmoebaConstants.PASSWORD_AB.length())));
	return sb.toString();
}

}
