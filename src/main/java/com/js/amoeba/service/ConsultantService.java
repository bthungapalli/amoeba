
package com.js.amoeba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.amoeba.dao.ConsultantDao;
import com.js.amoeba.domain.Consultant;
import com.js.amoeba.domain.User;
import com.js.amoeba.exception.AmoebaException;



@Service
public class ConsultantService {
	
	@Autowired
	private ConsultantDao consultantDao;
	
	@Transactional
	public void saveConsultant(Consultant consultant) throws Exception{
		consultantDao.saveConsultant(consultant);
	}
	
	@Transactional
	public List<User> getAllConsultant() throws AmoebaException{
		
		return consultantDao.fetchAllConsultantById();
	}
	
	@Transactional
	public User getConsultantById(Integer u_id) throws AmoebaException{
		return consultantDao.fetchConsultantById(u_id);
	}

	@Transactional
	public List<User> getForms(User user) throws AmoebaException{
		
		return consultantDao.getForms(user); 
	}
	
	public void updateUserForm(int u_id,int  f_id) throws Exception{
		consultantDao.updateForm(u_id, f_id);
	}

	public void updateUser(Consultant consultant, int u_Id) throws Exception {
		consultantDao.updateUser(consultant,u_Id);
		
	}

	
}
