package com.js.amoeba.domain;

import org.springframework.data.annotation.Id;

public class Consultant {

	@Id
	private int c_id;
	private int experience;
	private String specialization;
	private int avalibality;
	private  int formId;
	private int uId;
	private int simultaneous;
	private String subCat;
	
	
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
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization =specialization;
	}
	public int getAvalibality() {
		return avalibality;
	}
	public void setAvalibality(int avalibality) {
		this.avalibality = avalibality;
	}
	public int getFormId() {
		return formId;
	}
	public void setFormId(int formId) {
		this.formId = formId;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public int getSimultaneous() {
		return simultaneous;
	}
	public void setSimultaneous(int simultaneous) {
		simultaneous = simultaneous;
	}
	public String getSubCat() {
		return subCat;
	}
	public void setSubCat(String subCat) {
		this.subCat = subCat;
	}
}
