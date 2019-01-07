package com.fappy.javamodule.builder.dto;

import java.util.Set;

import com.fappy.javamodule.domain.entity.Profile;
import com.fappy.javamodule.domain.entity.User;
import com.fappy.javamodule.domain.entity.space.SpaceSlot;
import com.fappy.javamodule.dto.UrgenceContactDTO;

public class UrgenceContactDTOBuilder {

	private UrgenceContactDTO dto;
	
	public UrgenceContactDTOBuilder() {
		super();
		this.dto = new UrgenceContactDTO();
	}
	
	public UrgenceContactDTO build() {
		return this.dto;
	}
	
	public UrgenceContactDTOBuilder withUserAndSpaceId(User user, long spaceId) {
		this.dto.setUsername(user.getUsername());
		this.setFieldsByProfil(user.getProfile());
		this.setLink(user.getSpaceSlots(), spaceId);
		return this;
	}
	
	/**
	 * 
	 * @param spaceSlots
	 * @param spaceId
	 */
	private void setLink(Set<SpaceSlot> spaceSlots, long spaceId) {
		spaceSlots.stream()
			.filter(slot -> slot.getFamilySpace().getId() == spaceId)
			.findFirst()
			.ifPresent(slot -> this.dto.setLink(slot.getFamilyLink().getName()));
	}

	/**
	 * 
	 * @param profile
	 */
	private void setFieldsByProfil(Profile profile) {
		if (null != profile) {
			this.dto.setFirstname(profile.getFirstname());
			this.dto.setLastname(profile.getLastname());
			this.dto.setMobile(profile.getMobileNumber());
			this.dto.setPhone(profile.getPhoneNumber());
		}
	}
}
