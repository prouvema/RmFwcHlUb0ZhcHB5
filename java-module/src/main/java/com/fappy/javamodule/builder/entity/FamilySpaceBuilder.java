package com.fappy.javamodule.builder.entity;

import java.util.stream.Collectors;

import com.fappy.javamodule.domain.entity.space.FamilySpace;
import com.fappy.javamodule.dto.FamilySpaceDTO;
import com.fappy.javamodule.service.ReferenceService;
import com.fappy.javamodule.service.SpaceRoleService;
import com.fappy.javamodule.service.UserService;

public class FamilySpaceBuilder {

	private UserService userService;
	private ReferenceService referenceService;
	private FamilySpace familySpace;
	private SpaceRoleService spaceRoleService;
	
	public FamilySpaceBuilder(ReferenceService referenceService, UserService userService, SpaceRoleService spaceRoleService) {
		super();
		this.userService = userService;
		this.referenceService = referenceService;
		this.spaceRoleService = spaceRoleService;
		this.familySpace = new FamilySpace();
	}
	
	public FamilySpaceBuilder withFamilySpace(FamilySpace familySpace) {
		this.familySpace = familySpace;
		return this;
	}
	
	public FamilySpace build() {
		return this.familySpace;
	}
	
	public FamilySpaceBuilder withFamilySpaceDTO(FamilySpaceDTO dto) {
		
		this.familySpace.setId(dto.getId());
		this.familySpace.setName(dto.getName());
		this.familySpace.setSpaceSlots(dto.getSpaceSlots().stream()
				.map(spaceSlotDTO -> new SpaceSlotBuilder(this.referenceService, this.userService, this.spaceRoleService)
						.withSpaceSlotDTO(spaceSlotDTO)
						.build(this.familySpace))
				.collect(Collectors.toSet()));
		
		return this;
	}
}
