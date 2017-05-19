package com.js.amoeba.domain;

import org.springframework.data.annotation.Id;

public class UserForm {
	@Id
 private int formId;
private double weight;
private double height;
private int age;
private String specialization;
private String majorCat;
private String subCat;
private String status;
private int uId;
private int c_id;
public int getFormId() {
	return formId;
}
public void setFormId(int formId) {
	this.formId = formId;
}
public double getWeight() {
	return weight;
}
public void setWeight(double weight) {
	this.weight = weight;
}
public double getHeight() {
	return height;
}
public void setHeight(double height) {
	this.height = height;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getSpecialization() {
	return specialization;
}
public void setSpecialization(String specialization) {
	this.specialization = specialization;
}
public String getMajorCat() {
	return majorCat;
}
public void setMajorCat(String majorCat) {
	this.majorCat = majorCat;
}
public String getSubCat() {
	return subCat;
}
public void setSubCat(String subCat) {
	this.subCat = subCat;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public int getuId() {
	return uId;
}
public void setuId(int uId) {
	this.uId = uId;
}
public int getC_id() {
	return c_id;
}
public void setC_id(int c_id) {
	this.c_id = c_id;
}
}
