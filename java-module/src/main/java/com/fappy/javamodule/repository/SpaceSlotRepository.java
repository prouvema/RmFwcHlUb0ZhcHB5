package com.fappy.javamodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fappy.javamodule.domain.entity.space.SpaceSlot;

public interface SpaceSlotRepository extends JpaRepository<SpaceSlot, Long> {

}
