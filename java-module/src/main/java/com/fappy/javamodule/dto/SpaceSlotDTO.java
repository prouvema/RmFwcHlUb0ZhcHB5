package com.fappy.javamodule.dto;

public class SpaceSlotDTO {

	private long id;
	private UserDTO user;
	private String familyLink;
	private String validationState;
	private String tokenValidation;
	private boolean urgenceContact;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public String getFamilyLink() {
		return familyLink;
	}
	public void setFamilyLink(String familyLink) {
		this.familyLink = familyLink;
	}
	public String getValidationState() {
		return validationState;
	}
	public void setValidationState(String validationState) {
		this.validationState = validationState;
	}
	public String getTokenValidation() {
		return tokenValidation;
	}
	public void setTokenValidation(String tokenValidation) {
		this.tokenValidation = tokenValidation;
	}
	public boolean isUrgenceContact() {
		return urgenceContact;
	}
	public void setUrgenceContact(boolean urgenceContact) {
		this.urgenceContact = urgenceContact;
	}
	
}
