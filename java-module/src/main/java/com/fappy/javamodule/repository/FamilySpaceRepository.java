package com.fappy.javamodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fappy.javamodule.domain.entity.space.FamilySpace;

public interface FamilySpaceRepository extends JpaRepository<FamilySpace, Long> {

}
