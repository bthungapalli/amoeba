package com.js.amoeba.domain;


import java.util.List;

import org.springframework.data.annotation.Id;

public class Consultant {
	
	@Id
	private int c_id;
	
	private int experience;
	private int  spec_Id;
	private int mainCat_Id;
	private int subSpec_Id;
	private int userId;
	private Form form;
	
	private List<Form> listForms;
	
	private List<User> user;
	
	
	
	
	
	
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
	public List<Form> getListForms() {
		return listForms;
	}
	public void setListForms(List<Form> listForms) {
		this.listForms = listForms;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
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
	public Form getForm() {
		return form;
	}
	public void setForm(Form form) {
		this.form = form;
	}
	
	
	
	
	

}
