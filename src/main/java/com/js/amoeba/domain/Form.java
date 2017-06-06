package com.js.amoeba.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Form {
	
	@Id
	private int f_id;
	
	private String title;
	private int age;
	private int weight;
	private int height;
	private int spec_Id;
	private int mainCat_Id;
	private int subSpec_Id;
	private int status;
	private  int c_id;
	private int  user_id;
	private String reportDescription;
	private Date submittedAt;
	private List<User> user;
	
	
	
	
	
	
	
	public Date getSubmittedAt() {
		return submittedAt;
	}
	public void setSubmittedAt(Date submittedAt) {
		this.submittedAt = submittedAt;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReportDescription() {
		return reportDescription;
	}
	public void setReportDescription(String reportDescription) {
		this.reportDescription = reportDescription;
	}
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
	
	
	

}
