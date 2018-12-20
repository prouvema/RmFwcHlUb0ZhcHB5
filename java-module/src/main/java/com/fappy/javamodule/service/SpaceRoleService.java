package com.fappy.javamodule.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fappy.javamodule.domain.entity.space.SpaceRole;
import com.fappy.javamodule.repository.SpaceRoleRepository;

@Service
public class SpaceRoleService {

	private SpaceRoleRepository spaceRoleRepository;
	
	public SpaceRoleService(SpaceRoleRepository spaceRoleRepository) {
		super();
		this.spaceRoleRepository = spaceRoleRepository;
	}
	
	public Optional<SpaceRole> findByName(String name) {
		return this.spaceRoleRepository.findSpaceRoleByName(name);
	}
	
	public Optional<SpaceRole> findById(long id) {
		return this.spaceRoleRepository.findById(id);
	}
}
