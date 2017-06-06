package com.js.amoeba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.amoeba.dao.MessageDao;
import com.js.amoeba.domain.Message;
import com.js.amoeba.exception.AmoebaException;


@Service
public class MessageService {
	
	@Autowired
	private MessageDao messageDao;
	
	@Transactional
	public void sendConsultantMessage(Message message) throws AmoebaException{
		
		messageDao.saveConsultantMessage(message);
	}
	
	@Transactional
	public void sendUserMessage(Message message) throws AmoebaException{
		
		messageDao.saveUserMessage(message);
	}
	
	@Transactional
	public List<Message> getConsultantMessage(String group_id) throws AmoebaException{
		return messageDao.getConsultantMessages(group_id);
	}
	
	@Transactional
	public List<Message> getUserMessage(int from_id ,int to_id ) throws AmoebaException{
		return messageDao.getUserMessages(from_id, to_id);
	}
}
