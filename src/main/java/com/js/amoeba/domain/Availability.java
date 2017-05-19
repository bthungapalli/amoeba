package com.js.amoeba.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class Availability  implements Serializable{
	
	private int availabilityid;
	 private String availabilitytype;
	 private int availabiltyhours;
	 private String availablitydaytype;
	public int getAvailabilityid() {
		return availabilityid;
	}
	public void setAvailabilityid(int availabilityid) {
		this.availabilityid = availabilityid;
	}
	public String getAvailabilitytype() {
		return availabilitytype;
	}
	public void setAvailabilitytype(String availabilitytype) {
		this.availabilitytype = availabilitytype;
	}
	public int getAvailabiltyhours() {
		return availabiltyhours;
	}
	public void setAvailabiltyhours(int availabiltyhours) {
		this.availabiltyhours = availabiltyhours;
	}
	public String getAvailablitydaytype() {
		return availablitydaytype;
	}
	public void setAvailablitydaytype(String availablitydaytype) {
		this.availablitydaytype = availablitydaytype;
	}
	
	/**
	 * @return the id
	 */
	
}
