package com.fappy.javamodule.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Profile extends AbstractEntity {
	
	private String firstname;
	
	private String lastname;
	
	@Column(name = "mobile_number", length = 12)
	private String mobileNumber;
	
	@Column(name = "phone_number", length = 12)
	private String phoneNumber;
	
	@OneToOne(optional = false)
	private User user;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
