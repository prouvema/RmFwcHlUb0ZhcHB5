package com.fappy.javamodule.builder.dto;

import com.fappy.javamodule.domain.entity.Profile;
import com.fappy.javamodule.domain.entity.User;
import com.fappy.javamodule.domain.entity.space.FamilySpace;
import com.fappy.javamodule.domain.entity.space.SpaceMessage;
import com.fappy.javamodule.dto.SpaceMessageDTO;

public class SpaceMessageDTOBuilder {

	private SpaceMessageDTO dto;
	
	public SpaceMessageDTOBuilder() {
		super();
		this.dto = new SpaceMessageDTO();
	}
	
	public SpaceMessageDTO build() {
		return this.dto;
	}
	
	public SpaceMessageDTOBuilder withSpaceMessage(SpaceMessage spaceMessage) {
		this.dto.setId(spaceMessage.getId());
		this.dto.setContent(spaceMessage.getContent());
		this.dto.setCreateTime(spaceMessage.getCreateTime());
		this.setSpaceId(spaceMessage.getFamilySpace());
		this.setUserFields(spaceMessage.getUser());
		return this;
	}

	private void setUserFields(User user) {
		if (null != user) {
			this.dto.setUsername(user.getUsername());
			this.setProfileFields(user.getProfile());
		}
	}

	private void setProfileFields(Profile profile) {
		if (null != profile) {
			this.dto.setFirstname(profile.getFirstname());
			this.dto.setLastname(profile.getLastname());
		}
	}

	private void setSpaceId(FamilySpace familySpace) {
		if (null != familySpace) {
			this.dto.setSpaceId(familySpace.getId());
		}
	}
}
