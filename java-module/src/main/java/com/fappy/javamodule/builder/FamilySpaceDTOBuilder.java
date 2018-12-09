package com.fappy.javamodule.builder;

import java.util.stream.Collectors;

import com.fappy.javamodule.domain.entity.space.FamilySpace;
import com.fappy.javamodule.dto.FamilySpaceDTO;

public class FamilySpaceDTOBuilder {

	private FamilySpaceDTO dto;
	
	public FamilySpaceDTOBuilder() {
		super();
		this.dto = new FamilySpaceDTO();
	}
	
	public FamilySpaceDTO lightBuild() {
		this.dto.setSpaceSlots(null);
		return this.dto;
	}
	
	public FamilySpaceDTOBuilder withFamilySpace(FamilySpace familySpace) {
		
		this.dto.setId(familySpace.getId());
		this.dto.setName(familySpace.getName());
		this.dto.setSpaceSlots(familySpace.getSpaceSlots().stream()
				.map(spaceSlot -> new SpaceSlotDTOBuilder().withSpaceSlot(spaceSlot).build())
				.collect(Collectors.toSet()));
		
		return this;
	}
	
}
