package com.fappy.javamodule.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Profile extends AbstractEntity {
	
	@Size(max = 124)
	@Column(length = 124)
	private String firstname;
	
	@Size(max = 124)
	@Column(length = 124)
	private String lastname;
	
	@Size(max = 12)
	@Column(name = "mobile_number", length = 12)
	private String mobileNumber;
	
	@Size(max = 12)
	@Column(name = "phone_number", length = 12)
	private String phoneNumber;
	
	private String address;
	
	@NotNull
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
