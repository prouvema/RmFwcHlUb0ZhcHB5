package com.fappy.javamodule.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fappy.javamodule.domain.entity.space.SpaceMessage;

public interface SpaceMessageRepository extends JpaRepository<SpaceMessage, Long> {

	@Query("SELECT m FROM SpaceMessage m WHERE m.familySpace.id = :spaceId")
	Page<SpaceMessage> findBySpaceId(@Param("spaceId") long spaceId, Pageable pageRequest);
}
