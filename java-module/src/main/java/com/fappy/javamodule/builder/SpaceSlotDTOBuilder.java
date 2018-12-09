package com.fappy.javamodule.builder;

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
		if (null != spaceSlot.getFamilyLink()) {
			this.dto.setFamilyLink(spaceSlot.getFamilyLink().getName());
		}
		this.dto.setTokenValidation(spaceSlot.getTokenValidation());
		this.dto.setUrgenceContact(spaceSlot.isUrgenceContact());
		this.dto.setValidationState(spaceSlot.getValidationState().name());
		this.dto.setUser(new UserDTOBuilder().withUser(spaceSlot.getUser()).build());
		
		return this;
	}
	
}
