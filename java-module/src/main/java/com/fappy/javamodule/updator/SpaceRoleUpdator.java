package com.fappy.javamodule.updator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fappy.javamodule.domain.entity.space.SpaceAccess;
import com.fappy.javamodule.domain.entity.space.SpaceRole;
import com.fappy.javamodule.dto.SpaceRoleDTO;
import com.fappy.javamodule.service.SpaceRoleService;

public class SpaceRoleUpdator {

	private SpaceRoleService spaceRoleService;
	private SpaceRole spaceRole;
	
	public SpaceRoleUpdator(SpaceRoleService spaceRoleService, SpaceRole spaceRole) {
		super();
		this.spaceRoleService = spaceRoleService;
		this.spaceRole = spaceRole;
	}
	
	public SpaceRole update() {
		return this.spaceRole;
	}
	
	public SpaceRoleUpdator withSpaceRoleDto(SpaceRoleDTO dto) {
		this.spaceRole.setName(dto.getName());
		this.deleteOldAccesses(dto.getAccesses());
		this.AddAccesses(dto.getAccesses());
		return this;
	}

	private void AddAccesses(List<String> accesseNames) {
		List<String> spaceAccesses = this.spaceRole.getSpaceAccesses().stream()
				.map(access -> access.getAuthority())
				.collect(Collectors.toList());
		accesseNames.stream()
				.filter(access -> !spaceAccesses.contains(access))
				.forEach(access -> {
					Optional<SpaceAccess> accessToAdd = this.spaceRoleService.findSpaceAccessByName(access);
					if (accessToAdd.isPresent()) {
						this.spaceRole.getSpaceAccesses().add(accessToAdd.get());
					}
				});
	}

	private void deleteOldAccesses(List<String> accesses) {
		List<SpaceAccess> accessesToDelete = new ArrayList<>();
		this.spaceRole.getSpaceAccesses().stream()
			.filter(access -> !accesses.contains(access.getAuthority()))
			.forEach(access -> accessesToDelete.add(access));
		this.spaceRole.getSpaceAccesses().removeAll(accessesToDelete);
	}
	
}
