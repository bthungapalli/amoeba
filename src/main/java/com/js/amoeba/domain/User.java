package com.js.amoeba.domain;

import org.springframework.data.annotation.Id;
import org.springframework.web.multipart.MultipartFile;

import com.js.amoeba.domain.DateTimeVars;

public class User  extends DateTimeVars{

	@Id
	private int id;
	
	private String email;
	private String password;
	private int role;
	private String firstName;
	//private String middleName;
	private String lastName;
	private String phone;
	private String address;
	private String gender;
	
	
	
	private boolean verification = false;
	
	private boolean confirmed = false;
	
	//private int mailAccount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isVerification() {
		return verification;
	}
	public void setVerification(boolean verification) {
		this.verification = verification;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	/*public int getMailAccount() {
		return mailAccount;
	}
	public void setMailAccount(int mailAccount) {
		this.mailAccount = mailAccount;
	}*/
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	
}
