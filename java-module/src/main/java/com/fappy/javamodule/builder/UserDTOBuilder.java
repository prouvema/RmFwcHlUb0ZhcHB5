package com.fappy.javamodule.builder;

import com.fappy.javamodule.domain.entity.Profile;
import com.fappy.javamodule.domain.entity.User;
import com.fappy.javamodule.dto.UserDTO;

public class UserDTOBuilder {

	private UserDTO dto;
	
	public UserDTOBuilder() {
		super();
		this.dto = new UserDTO();
	}
	
	public UserDTO build() {
		return this.dto;
	}
	
	public UserDTOBuilder withUser(User user) {
		
		this.dto.setUsername(user.getUsername());
		
		Profile profile = user.getProfile();
		if (null != profile) {
			this.dto.setFirstname(profile.getFirstname());
			this.dto.setLastname(profile.getLastname());
		}
		
		return this;
	}
}
