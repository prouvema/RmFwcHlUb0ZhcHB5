package com.fappy.javamodule.domain.entity.space;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fappy.javamodule.domain.entity.AbstractEntity;
import com.fappy.javamodule.domain.entity.User;

@Entity
@Table(name = "space_slot", uniqueConstraints={@UniqueConstraint(columnNames={"token_validation"})})
public class SpaceSlot extends AbstractEntity {

	@NotNull
	@ManyToOne(optional = false)
	private User user;
	
	@NotNull
	@JoinColumn(name = "family_space_id")
	@ManyToOne(optional = false)
	private FamilySpace familySpace;
	
	@JoinTable(
			name = "space_slot_space_role_join", 
			joinColumns = @JoinColumn(name = "space_slot_id"), 
			inverseJoinColumns = @JoinColumn(name = "space_role_id")
			)
	@ManyToMany
	private Set<SpaceRole> spaceRoles = new HashSet<>();
	
	@NotNull
	@JoinColumn(name = "family_link_id")
	@ManyToOne(optional = false)
	private FamilyLink familyLink;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "validation_state", nullable = false)
	private ValidationState validationState;
	
	@Column(name = "token_validation", length = 191)
	private String tokenValidation;
	
	@NotNull
	@Column(name = "urgence_contact", nullable = false)
	private boolean urgenceContact = false;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public FamilySpace getFamilySpace() {
		return familySpace;
	}

	public void setFamilySpace(FamilySpace familySpace) {
		this.familySpace = familySpace;
	}

	public Set<SpaceRole> getSpaceRoles() {
		return spaceRoles;
	}

	public void setSpaceRoles(Set<SpaceRole> spaceRoles) {
		this.spaceRoles = spaceRoles;
	}

	public FamilyLink getFamilyLink() {
		return familyLink;
	}

	public void setFamilyLink(FamilyLink familyLink) {
		this.familyLink = familyLink;
	}

	public ValidationState getValidationState() {
		return validationState;
	}

	public void setValidationState(ValidationState validationState) {
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
