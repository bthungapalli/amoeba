package com.js.amoeba.domain;

import java.sql.Timestamp;

public class DateTimeVars {

	
	private Timestamp createdAt;
	private Timestamp expiryDate;
	
	/**
	 * @return the createdAt
	 */
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Timestamp expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
}
