package com.js.amoeba.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Message {
	
	@Id
	private int msg_Id;
	
	private int from_Id;
	private int to_Id;
	private String message;
	private String group_Id;
	private Date sent_date;
	
	private List<User> messageUser;
	
	
	
	
	
	public List<User> getUser() {
		return messageUser;
	}
	public void setUser(List<User> messageUser) {
		this.messageUser = messageUser;
	}
	public Date getSent_date() {
		return sent_date;
	}
	public void setSent_date(Date sent_date) {
		this.sent_date = sent_date;
	}
	public int getMsg_Id() {
		return msg_Id;
	}
	public void setMsg_Id(int msg_Id) {
		this.msg_Id = msg_Id;
	}
	public int getFrom_Id() {
		return from_Id;
	}
	public void setFrom_Id(int from_Id) {
		this.from_Id = from_Id;
	}
	public int getTo_Id() {
		return to_Id;
	}
	public void setTo_Id(int to_Id) {
		this.to_Id = to_Id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getGroup_Id() {
		return group_Id;
	}
	public void setGroup_Id(String group_Id) {
		this.group_Id = group_Id;
	}
	
	
	
	

}
