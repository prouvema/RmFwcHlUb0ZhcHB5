package com.fappy.javamodule.updator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fappy.javamodule.domain.entity.User;
import com.fappy.javamodule.domain.entity.space.FamilyLink;
import com.fappy.javamodule.domain.entity.space.SpaceRole;
import com.fappy.javamodule.domain.entity.space.SpaceSlot;
import com.fappy.javamodule.domain.entity.space.ValidationState;
import com.fappy.javamodule.dto.SpaceSlotDTO;
import com.fappy.javamodule.service.ReferenceService;
import com.fappy.javamodule.service.SpaceRoleService;
import com.fappy.javamodule.service.UserService;

public class SpaceSlotUpdator {

	private SpaceSlot spaceSlot;
	private UserService userService;
	private SpaceRoleService spaceRoleService;
	private ReferenceService referenceService;
	
	public SpaceSlotUpdator(
			SpaceSlot spaceSlot, 
			UserService userService,
			SpaceRoleService spaceRoleService,
			ReferenceService referenceService) 
	{
		super();
		this.spaceSlot = spaceSlot;
		this.userService = userService;
		this.spaceRoleService = spaceRoleService;
		this.referenceService = referenceService;
	}
	
	/**
	 * 
	 * @return
	 */
	public SpaceSlot update() {
		return this.spaceSlot;
	}
	
	/**
	 * 
	 * @param dto
	 * @return
	 */
	public SpaceSlotUpdator withSpaceSlotDTO(SpaceSlotDTO dto) {
		this.updateSpaceSlotUser(dto);
		this.deleteOldRoles(dto.getRoles());
		this.AddRoles(dto.getRoles());
		this.updateSpaceSlotFamilyLink(dto.getFamilyLink());
		this.updateSpaceSlotValidationState(dto.getValidationState());
		this.spaceSlot.setTokenValidation(dto.getTokenValidation());
		this.spaceSlot.setUrgenceContact(dto.isUrgenceContact());
		return this;
	}

	/**
	 * 
	 * @param validationState
	 */
	private void updateSpaceSlotValidationState(String validationState) {
		this.spaceSlot.setValidationState(ValidationState.valueOf(validationState));
	}

	/**
	 * 
	 * @param familyLink
	 */
	private void updateSpaceSlotFamilyLink(String linkDto) {
		if (this.spaceSlot.getFamilyLink().getName() != linkDto) {
			Optional<FamilyLink> familyLinkToSet = this.referenceService.findFamilyLinkByName(linkDto);
			if (familyLinkToSet.isPresent()) {
				this.spaceSlot.setFamilyLink(familyLinkToSet.get());
			}
		}
	}

	/**
	 * 
	 * @param spaceSlots
	 */
	private void deleteOldRoles(List<String> roleDtos) {
		List<SpaceRole> rolesToDelete = new ArrayList<>();
		
		this.spaceSlot.getSpaceRoles().stream()
			.filter(role -> !roleDtos.contains(role.getName()))
			.forEach(role -> rolesToDelete.add(role));
		
		this.spaceSlot.getSpaceRoles().removeAll(rolesToDelete);
	}

	/**
	 * 
	 * @param dto
	 */
	private void AddRoles(List<String> roleDtos) {
		
		List<String> roles = this.spaceSlot.getSpaceRoles().stream()
			.map(role -> role.getName())
			.collect(Collectors.toList());
		
		roleDtos.stream()
			.filter(roleDto -> !roles.contains(roleDto))
			.forEach(roleDto -> {
				Optional<SpaceRole> roleToAdd = this.spaceRoleService.findByName(roleDto);
				if (roleToAdd.isPresent()) {
					this.spaceSlot.getSpaceRoles().add(roleToAdd.get());
				}
			});
	}

	/**
	 * 
	 * @param dto
	 */
	private void updateSpaceSlotUser(SpaceSlotDTO dto) {
		if (null == dto.getUser()) {
			this.spaceSlot.setUser(null);
		} else {
			Optional<User> user = this.userService.findUserByUsername(dto.getUser().getUsername());
			if (this.hasNullOrNotSameUser(user)) {
				this.spaceSlot.setUser(user.get());
			}
		}
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	private boolean hasNullOrNotSameUser(Optional<User> user) {
		return user.isPresent() && (null == this.spaceSlot.getUser() || user.get().getId() != this.spaceSlot.getUser().getId());
	}
	
}
