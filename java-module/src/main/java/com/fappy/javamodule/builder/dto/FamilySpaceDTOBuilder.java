package com.fappy.javamodule.builder.dto;

import java.util.stream.Collectors;

import com.fappy.javamodule.domain.entity.space.FamilySpace;
import com.fappy.javamodule.dto.FamilySpaceDTO;

public class FamilySpaceDTOBuilder {

	private FamilySpace familySpace;
	private FamilySpaceDTO dto;
	
	public FamilySpaceDTOBuilder() {
		super();
		this.dto = new FamilySpaceDTO();
	}
	
	public FamilySpaceDTOBuilder withFamilySpace(FamilySpace familySpace) {
		this.familySpace = familySpace;
		return this;
	}
	
	public FamilySpaceDTO build() {
		this.setCommonFields();
		this.dto.setSpaceSlots(this.familySpace.getSpaceSlots().stream()
				.map(spaceSlot -> new SpaceSlotDTOBuilder().withSpaceSlot(spaceSlot).buildByFamilySpace())
				.collect(Collectors.toSet()));
		return this.dto;
	}
	
	public FamilySpaceDTO lightBuild() {
		this.setCommonFields();
		return this.dto;
	}

	public FamilySpaceDTO buildBySpaceSlot(long id) {
		this.setCommonFields();
		this.dto.setSpaceSlots(this.familySpace.getSpaceSlots().stream()
				.filter(slot -> slot.getId() != id)
				.map(spaceSlot -> new SpaceSlotDTOBuilder().withSpaceSlot(spaceSlot).buildByFamilySpace())
				.collect(Collectors.toSet()));
		return this.dto;
	}
	
	private void setCommonFields() {
		this.dto.setId(this.familySpace.getId());
		this.dto.setName(this.familySpace.getName());
	}
}
