package com.fappy.javamodule.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fappy.javamodule.domain.entity.space.FamilyLink;

public interface FamilyLinkRepository extends JpaRepository<FamilyLink, Long> {

	Optional<FamilyLink> findFamilyLinkByName(String name);
	
}
