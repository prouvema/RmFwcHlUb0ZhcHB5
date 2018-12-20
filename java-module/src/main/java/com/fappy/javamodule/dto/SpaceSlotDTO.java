package com.fappy.javamodule.dto;

import java.util.List;

public class SpaceSlotDTO extends AbstractDTO {

	private UserDTO user;
	private String familyLink;
	private String validationState;
	private String tokenValidation;
	private boolean urgenceContact;
	private List<String> roles;
	
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
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
}
