package com.js.amoeba.domain;

import java.io.Serializable;

public class UserDetails  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1223573822232719312L;
	
	private String userId;
	private String email;
	private String firstName;
	private String lastName;
	private String contactNo;
	private int mailAccount;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public int getMailAccount() {
		return mailAccount;
	}
	public void setMailAccount(int mailAccount) {
		this.mailAccount = mailAccount;
	}
}
