package com.fappy.javamodule.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

	private String username;
	private String firstname;
	private String lastname;
	private String mobile;
	private String phone;
	private List<SpaceSlotDTO> spaceSlots = new ArrayList<>();
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
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
	public List<SpaceSlotDTO> getSpaceSlots() {
		return spaceSlots;
	}
	public void setSpaceSlots(List<SpaceSlotDTO> spaceSlots) {
		this.spaceSlots = spaceSlots;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
