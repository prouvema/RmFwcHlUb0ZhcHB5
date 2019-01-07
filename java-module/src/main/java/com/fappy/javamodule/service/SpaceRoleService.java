package com.fappy.javamodule.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fappy.javamodule.domain.entity.space.SpaceAccess;
import com.fappy.javamodule.domain.entity.space.SpaceRole;
import com.fappy.javamodule.repository.SpaceAccessRepository;
import com.fappy.javamodule.repository.SpaceRoleRepository;

@Service
public class SpaceRoleService {

	final private SpaceRoleRepository spaceRoleRepository;
	final private SpaceAccessRepository spaceAccessRepository;
	
	public SpaceRoleService(SpaceRoleRepository spaceRoleRepository, SpaceAccessRepository spaceAccessRepository) {
		super();
		this.spaceRoleRepository = spaceRoleRepository;
		this.spaceAccessRepository = spaceAccessRepository;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Optional<SpaceRole> findSpaceRoleByName(String name) {
		return this.spaceRoleRepository.findSpaceRoleByName(name);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Optional<SpaceRole> findById(long id) {
		return this.spaceRoleRepository.findById(id);
	}
	
	/**
	 * 
	 * @param spaceRole
	 * @return
	 */
	public SpaceRole saveSpaceRole(SpaceRole spaceRole) {
		return this.spaceRoleRepository.saveAndFlush(spaceRole);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<SpaceRole> findAllSpaceRoles() {
		return this.spaceRoleRepository.findAll();
	}
	
	/**
	 * 
	 * @param id
	 */
	public void deleteSpaceRoleById(long id) {
		this.spaceRoleRepository.deleteById(id);
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Optional<SpaceAccess> findSpaceAccessByName(String name) {
		return this.spaceAccessRepository.findSpaceAccessByAuthority(name);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<SpaceAccess> findAllSpaceAccesses() {
		return this.spaceAccessRepository.findAll();
	}
	
}
