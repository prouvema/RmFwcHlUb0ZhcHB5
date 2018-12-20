package com.fappy.javamodule.builder.entity;

import java.util.List;
import java.util.Optional;

import com.fappy.javamodule.domain.entity.User;
import com.fappy.javamodule.domain.entity.space.FamilyLink;
import com.fappy.javamodule.domain.entity.space.FamilySpace;
import com.fappy.javamodule.domain.entity.space.SpaceRole;
import com.fappy.javamodule.domain.entity.space.SpaceSlot;
import com.fappy.javamodule.domain.entity.space.ValidationState;
import com.fappy.javamodule.dto.SpaceSlotDTO;
import com.fappy.javamodule.service.ReferenceService;
import com.fappy.javamodule.service.SpaceRoleService;
import com.fappy.javamodule.service.UserService;

public class SpaceSlotBuilder {
	
	private UserService userService;
	private ReferenceService referenceService;
	private SpaceSlot spaceSlot;
	private SpaceRoleService spaceRoleService; 
	
	public SpaceSlotBuilder(
			ReferenceService referenceService, 
			UserService userService, 
			SpaceRoleService spaceRoleService) 
	{
		super();
		this.referenceService = referenceService;
		this.userService = userService;
		this.spaceRoleService = spaceRoleService;
		this.spaceSlot = new SpaceSlot();
	}
	
	public SpaceSlot build(FamilySpace familySpace) {
		this.spaceSlot.setFamilySpace(familySpace);
		return this.spaceSlot;
	}

	public SpaceSlotBuilder withSpaceSlotDTO(SpaceSlotDTO dto) {
		this.spaceSlot.setId(dto.getId());
		this.spaceSlot.setTokenValidation(dto.getTokenValidation());
		this.spaceSlot.setUrgenceContact(dto.isUrgenceContact());
		this.setSpaceSlotValidationState(dto);
		this.setSpaceSlotFamilyLink(dto);
		this.setSpaceSlotUser(dto);
		this.setSpaceSlotRole(dto.getRoles());
		return this;
	}

	/**
	 * 
	 * @param roleDtos
	 */
	private void setSpaceSlotRole(List<String> roleDtos) {
			roleDtos.stream()
				.forEach(roleDto -> {
					Optional<SpaceRole> roleToAdd = this.spaceRoleService.findByName(roleDto);
					if (roleToAdd.isPresent()) {
						this.spaceSlot.getSpaceRoles().add(roleToAdd.get());
					}
				});
	}

	/**
	 * Set family link
	 * @param dto
	 */
	private void setSpaceSlotFamilyLink(SpaceSlotDTO dto) {
		Optional<FamilyLink> familyLink = this.referenceService.findFamilyLinkByName(dto.getFamilyLink());
		if (familyLink.isPresent()) {
			this.spaceSlot.setFamilyLink(familyLink.get());
		}
	}

	/**
	 * Set user
	 * @param dto
	 */
	private void setSpaceSlotUser(SpaceSlotDTO dto) {
		if (null != dto.getUser() && null != dto.getUser().getUsername()) {
			Optional<User> user = this.userService.findUserByUsername(dto.getUser().getUsername());
			if (user.isPresent()) {
				this.spaceSlot.setUser(user.get());
			}
		}
	}
	
	/**
	 * Set validation state
	 * @param dto
	 */
	private void setSpaceSlotValidationState(SpaceSlotDTO dto) {
		if (null != dto.getValidationState()) {
			this.spaceSlot.setValidationState(ValidationState.valueOf(dto.getValidationState()));
		} else {
			this.spaceSlot.setValidationState(ValidationState.WAITING);
		}
	}
}
