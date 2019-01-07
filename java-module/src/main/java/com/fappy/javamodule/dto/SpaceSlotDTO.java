package com.fappy.javamodule.dto;

import java.util.Set;

public class SpaceSlotDTO extends AbstractDTO {

	private UserDTO user;
	private String familyLink;
	private String validationState;
	private String tokenValidation;
	private boolean urgenceContact;
	private Set<String> roles;
	private FamilySpaceDTO space;
	
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
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	public FamilySpaceDTO getSpace() {
		return space;
	}
	public void setSpace(FamilySpaceDTO space) {
		this.space = space;
	}
	
}
