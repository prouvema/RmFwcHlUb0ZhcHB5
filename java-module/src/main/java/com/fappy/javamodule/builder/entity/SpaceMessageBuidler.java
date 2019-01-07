package com.fappy.javamodule.builder.entity;

import java.util.Optional;

import com.fappy.javamodule.domain.entity.User;
import com.fappy.javamodule.domain.entity.space.FamilySpace;
import com.fappy.javamodule.domain.entity.space.SpaceMessage;
import com.fappy.javamodule.dto.SpaceMessageDTO;
import com.fappy.javamodule.service.FamilySpaceService;
import com.fappy.javamodule.service.UserService;

public class SpaceMessageBuidler {

	private final UserService userService;
	private final FamilySpaceService familySpaceService;
	
	private SpaceMessage spaceMessage;
	
	public SpaceMessageBuidler(UserService userService, FamilySpaceService familySpaceService) {
		super();
		this.userService = userService;
		this.familySpaceService = familySpaceService;
		this.spaceMessage = new SpaceMessage();
	}
	
	public SpaceMessage build() {
		return this.spaceMessage;
	}
	
	public SpaceMessageBuidler withSpaceMessageDto(SpaceMessageDTO dto) {
		this.spaceMessage.setContent(dto.getContent());
		this.setFamilySpace(dto.getSpaceId());
		this.setUser(dto.getUsername());
		return this;
	}

	private void setUser(String username) {
		Optional<User> user = this.userService.findUserByUsername(username);
		if (user.isPresent()) {
			this.spaceMessage.setUser(user.get());
		}
	}

	private void setFamilySpace(long spaceId) {
		Optional<FamilySpace> space = this.familySpaceService.findById(spaceId);
		if (space.isPresent()) {
			this.spaceMessage.setFamilySpace(space.get());
		}
	}
	
}
