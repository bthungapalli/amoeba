package com.js.amoeba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.amoeba.dao.UserFormDao;
import com.js.amoeba.domain.Consultant;
import com.js.amoeba.domain.UserForm;
import com.js.amoeba.exception.AmoebaDaoException;



@Service
public class UserFormService {

	@Autowired
	private UserFormDao userformDao;
	
	
	
	@Transactional
	public void saveForm(UserForm userform) throws Exception {
		System.out.println("hii");
		userformDao.saveForm(userform);
	}
	/*@Transactional
	public List<UserForm> getAllUserForm() throws AmoebaDaoException{
		return userformDao.fetchAllFormById();
	}*/
}
