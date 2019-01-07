package com.fappy.javamodule.builder.entity;

import java.util.List;
import java.util.Optional;

import com.fappy.javamodule.domain.entity.space.SpaceAccess;
import com.fappy.javamodule.domain.entity.space.SpaceRole;
import com.fappy.javamodule.dto.SpaceRoleDTO;
import com.fappy.javamodule.service.SpaceRoleService;

public class SpaceRoleBuilder {

	private SpaceRoleService spaceRoleService;
	private SpaceRole spaceRole;
	
	public SpaceRoleBuilder(SpaceRoleService spaceRoleService) {
		super();
		this.spaceRole = new SpaceRole();
		this.spaceRoleService = spaceRoleService;
	}
	
	public SpaceRole build() {
		return this.spaceRole;
	}
	
	public SpaceRoleBuilder withSpaceRoleDto(SpaceRoleDTO dto) {
		this.spaceRole.setName(dto.getName());
		this.setAccesses(dto.getAccesses());
		return this;
	}

	private void setAccesses(List<String> accesses) {
		accesses.forEach(accessName -> {
			Optional<SpaceAccess> spaceAccess = this.spaceRoleService.findSpaceAccessByName(accessName);
			if (spaceAccess.isPresent()) {
				spaceRole.getSpaceAccesses().add(spaceAccess.get());
			}
		});
	}
}
