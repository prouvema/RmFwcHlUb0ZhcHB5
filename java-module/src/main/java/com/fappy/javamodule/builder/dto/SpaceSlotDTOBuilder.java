package com.fappy.javamodule.builder.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.fappy.javamodule.domain.entity.space.SpaceRole;
import com.fappy.javamodule.domain.entity.space.SpaceSlot;
import com.fappy.javamodule.dto.FamilySpaceDTO;
import com.fappy.javamodule.dto.SpaceSlotDTO;
import com.fappy.javamodule.dto.UserDTO;

public class SpaceSlotDTOBuilder {

	private SpaceSlot spaceSlot;
	private SpaceSlotDTO dto;
	
	public SpaceSlotDTOBuilder() {
		super();
		this.dto = new SpaceSlotDTO();
	}
	
	public SpaceSlotDTO build() {
		this.setCommonFields();
		this.setUser();
		this.setSpace();
		return this.dto;
	}
	
	public SpaceSlotDTO buildByFamilySpace() {
		this.setCommonFields();
		this.setUser();
		return this.dto;
	}
	
	public SpaceSlotDTO buildByUser() {
		this.setCommonFields();
		this.setUserByUser();
		this.setSpaceByUser();
		return this.dto;
	}

	public SpaceSlotDTOBuilder withSpaceSlot(SpaceSlot spaceSlot) {
		this.spaceSlot = spaceSlot;
		return this;
	}
	
	private void setSpace() {
		this.dto.setSpace(new FamilySpaceDTOBuilder().withFamilySpace(this.spaceSlot.getFamilySpace()).buildBySpaceSlot(this.spaceSlot.getId()));
	}
	
	private void setSpaceByUser() {
		FamilySpaceDTO familySpaceDTO = new FamilySpaceDTO();
		familySpaceDTO.setId(this.spaceSlot.getFamilySpace().getId());
		familySpaceDTO.setName(this.spaceSlot.getFamilySpace().getName());
		this.dto.setSpace(familySpaceDTO);
	}
	
	private void setUser() {
		this.dto.setUser(new UserDTOBuilder().withUser(this.spaceSlot.getUser()).build());
	}
	
	private void setCommonFields() {
		this.dto.setId(this.spaceSlot.getId());
		this.setFamilyLink();
		this.dto.setTokenValidation(this.spaceSlot.getTokenValidation());
		this.dto.setUrgenceContact(this.spaceSlot.isUrgenceContact());
		this.setValidationState();
		this.setRoles();
	}

	/**
	 * 
	 */
	private void setUserByUser() {
		UserDTO userDTO = new UserDTO();
		if (null != this.spaceSlot.getUser()) {
			userDTO.setUsername(this.spaceSlot.getUser().getUsername());
		}
		this.dto.setUser(userDTO);
	}
	
	/**
	 * 
	 */
	private void setValidationState() {
		if (null != this.spaceSlot.getValidationState()) {
			this.dto.setValidationState(this.spaceSlot.getValidationState().name());
		}
	}
	
	/**
	 * 
	 * @param familyLink
	 */
	private void setFamilyLink() {
		if (null != this.spaceSlot.getFamilyLink()) {
			this.dto.setFamilyLink(this.spaceSlot.getFamilyLink().getName());
		}
	}

	/**
	 * 
	 * @param spaceRoles
	 */
	private void setRoles() {
		Set<String> roles = this.spaceSlot.getSpaceRoles().stream()
				.map(SpaceRole::getName)
				.collect(Collectors.toSet());
			this.dto.setRoles(roles);
	}
	
}
