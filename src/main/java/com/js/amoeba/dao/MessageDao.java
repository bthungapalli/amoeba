package com.js.amoeba.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.js.amoeba.domain.Message;
import com.js.amoeba.exception.AmoebaException;



@Repository
public interface MessageDao {
	
	//@Insert("insert into message values (from_Id=#{message.from_Id}, to_Id=#{message.to_Id}, message=#{message.message}, sent_date=NOW(), group_Id=123)")
	public void saveConsultantMessage(@Param("message") Message message) throws AmoebaException;
	
	//@Insert("insert into message values (from_Id=#{message.from_Id}, to_Id=#{message.to_Id}, message=#{message.message}, sent_date=#{message.sent_date}, group_Id='User:'+#{message.to_Id}+'User:'+#{message.from_Id})")
	public void saveUserMessage(Message message) throws AmoebaException;

	 
	public List<Message> getConsultantMessages(@Param("group_id") String group_id) throws AmoebaException;
	
	public List<Message> getUserMessages( @Param("to_id") int to_id,@Param("from_id") int from_id) throws AmoebaException;
}
