package com.fappy.javamodule.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fappy.javamodule.domain.entity.space.SpaceAccess;

public interface SpaceAccessRepository extends JpaRepository<SpaceAccess, Long> {

	Optional<SpaceAccess> findSpaceAccessByAuthority(String authority);
	
}
