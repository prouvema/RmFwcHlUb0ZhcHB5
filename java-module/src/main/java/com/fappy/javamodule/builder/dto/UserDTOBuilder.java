package com.fappy.javamodule.builder.dto;

import com.fappy.javamodule.domain.entity.Profile;
import com.fappy.javamodule.domain.entity.User;
import com.fappy.javamodule.dto.SpaceSlotDTO;
import com.fappy.javamodule.dto.UserDTO;

public class UserDTOBuilder {

	private User user;
	private UserDTO dto;
	
	public UserDTOBuilder() {
		super();
		this.dto = new UserDTO();
	}
	
	public UserDTO build() {
		this.setUsername();
		this.setFieldsByProfil();
		this.setSpaceSlots();
		return this.dto;
	}
	
	public UserDTO buildUrgenceContact() {
		this.setUsername();
		this.setFieldsByProfil();
		return this.dto;
	}
	
	public UserDTOBuilder withUser(User user) {
		this.user = user;
		return this;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////
	
	private void setUsername() {
		this.dto.setUsername(this.user.getUsername());
	}
	
	/**
	 * 
	 * @param spaceSlots
	 */
	private void setSpaceSlots() {
		this.user.getSpaceSlots().forEach(spaceSlot -> {
			SpaceSlotDTO spaceSlotDTO = new SpaceSlotDTOBuilder()
				.withSpaceSlot(spaceSlot)
				.buildByUser();
			this.dto.getSpaceSlots().add(spaceSlotDTO);
		});
	}

	/**
	 * 
	 * @param profile
	 */
	private void setFieldsByProfil() {
		Profile profile = this.user.getProfile();
		if (null != profile) {
			this.dto.setFirstname(profile.getFirstname());
			this.dto.setLastname(profile.getLastname());
			this.dto.setMobile(profile.getMobileNumber());
			this.dto.setPhone(profile.getPhoneNumber());
		}
	}
}
