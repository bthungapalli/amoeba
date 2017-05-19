package com.js.amoeba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.amoeba.dao.ConsultantDao;
import com.js.amoeba.domain.Consultant;
import com.js.amoeba.exception.AmoebaDaoException;

@Service
public class ConsultantService {
	
@Autowired
private ConsultantDao consultantDao;
	
	@Transactional
	public void saveConsultant(Consultant consultant) throws Exception {
		System.out.println("hii");
		consultantDao.saveConsultant(consultant);
	}
	@Transactional
	public List<Consultant> getAllConsultant() throws AmoebaDaoException{
		return consultantDao.fetchAllConsultantById();
	}
	 @Transactional
	 public Consultant getConsultantById(Integer c_id) throws AmoebaDaoException{
	  return consultantDao.fetchConsultantById(c_id);
	 }

}