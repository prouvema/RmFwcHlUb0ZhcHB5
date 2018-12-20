package com.fappy.javamodule.builder.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fappy.javamodule.domain.entity.space.FamilyLink;
import com.fappy.javamodule.domain.entity.space.SpaceRole;
import com.fappy.javamodule.domain.entity.space.SpaceSlot;
import com.fappy.javamodule.dto.SpaceSlotDTO;

public class SpaceSlotDTOBuilder {

	private SpaceSlotDTO dto;
	
	public SpaceSlotDTOBuilder() {
		super();
		this.dto = new SpaceSlotDTO();
	}
	
	public SpaceSlotDTO build() {
		return this.dto;
	}
	
	public SpaceSlotDTOBuilder withSpaceSlot(SpaceSlot spaceSlot) {
		
		this.dto.setId(spaceSlot.getId());
		this.setFamilyLink(spaceSlot.getFamilyLink());
		this.dto.setTokenValidation(spaceSlot.getTokenValidation());
		this.dto.setUrgenceContact(spaceSlot.isUrgenceContact());
		this.dto.setValidationState(spaceSlot.getValidationState().name());
		this.dto.setUser(new UserDTOBuilder().withUser(spaceSlot.getUser()).build());
		this.setRoles(spaceSlot.getSpaceRoles());
		
		return this;
	}

	/**
	 * 
	 * @param familyLink
	 */
	private void setFamilyLink(FamilyLink familyLink) {
		if (null != familyLink) {
			this.dto.setFamilyLink(familyLink.getName());
		}
	}

	/**
	 * 
	 * @param spaceRoles
	 */
	private void setRoles(Set<SpaceRole> spaceRoles) {
		List<String> roles = spaceRoles.stream()
				.map(SpaceRole::getName)
				.collect(Collectors.toList());
			this.dto.setRoles(roles);
	}
	
}
