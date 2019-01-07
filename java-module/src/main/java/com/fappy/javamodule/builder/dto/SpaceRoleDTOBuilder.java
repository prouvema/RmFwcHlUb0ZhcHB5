package com.fappy.javamodule.builder.dto;

import java.util.Set;

import com.fappy.javamodule.domain.entity.space.SpaceAccess;
import com.fappy.javamodule.domain.entity.space.SpaceRole;
import com.fappy.javamodule.dto.SpaceRoleDTO;

public class SpaceRoleDTOBuilder {

	private SpaceRoleDTO spaceRoleDto;

	public SpaceRoleDTOBuilder() {
		super();
		this.spaceRoleDto = new SpaceRoleDTO();
	}
	
	public SpaceRoleDTO build() {
		return this.spaceRoleDto;
	}
	
	public SpaceRoleDTOBuilder withSpaceRole(SpaceRole spaceRole) {
		this.spaceRoleDto.setName(spaceRole.getName());
		this.setAccesses(spaceRole.getSpaceAccesses());
		return this;
	}

	private void setAccesses(Set<SpaceAccess> spaceAccesses) {
		spaceAccesses.stream()
			.map(SpaceAccess::getAuthority)
			.forEach(accessName -> this.spaceRoleDto.getAccesses().add(accessName));
	}
	
}
