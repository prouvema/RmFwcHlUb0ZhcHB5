package com.fappy.javamodule.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fappy.javamodule.domain.entity.space.SpaceRole;

public interface SpaceRoleRepository extends JpaRepository<SpaceRole, Long> {

	Optional<SpaceRole> findSpaceRoleByName(String name);
	
}
