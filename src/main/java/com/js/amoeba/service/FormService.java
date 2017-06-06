package com.js.amoeba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.amoeba.dao.FormDao;
import com.js.amoeba.domain.Form;
import com.js.amoeba.exception.AmoebaException;



@Service
public class FormService {
	
	@Autowired
	private FormDao formDao;
	
	@Transactional
	public void saveForm(Form form) throws Exception{
		formDao.saveForm(form);
		
	}
	
	@Transactional
	public Form getUserFormById(Integer f_id) throws AmoebaException{
		
	return	formDao.getUserFormById(f_id);
	}
	
	@Transactional
	public List<Form> getFormById(Integer user_id) throws AmoebaException{
		
	return	formDao.getFormById(user_id);
	}
	

}
