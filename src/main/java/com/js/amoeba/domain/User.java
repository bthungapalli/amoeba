package com.js.amoeba.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class User {
	@Id
	private int userId;
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private int role;
	private String gender;
	private String number;
	private String address;
	private int c_id;
	private Date updatedAt;
	private Date createdAt;
	private int  spec_Id;
	private int mainCat_Id;
	private int subSpec_Id;
	
private boolean verification = false;
	
	private boolean confirmed = false;
	
	
	
	
	
	
	public int getSpec_Id() {
		return spec_Id;
	}
	public void setSpec_Id(int spec_Id) {
		this.spec_Id = spec_Id;
	}
	public int getMainCat_Id() {
		return mainCat_Id;
	}
	public void setMainCat_Id(int mainCat_Id) {
		this.mainCat_Id = mainCat_Id;
	}

	public int getSubSpec_Id() {
		return subSpec_Id;
	}
	public void setSubSpec_Id(int subSpec_Id) {
		this.subSpec_Id = subSpec_Id;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
