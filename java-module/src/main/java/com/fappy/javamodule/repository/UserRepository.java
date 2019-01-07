package com.fappy.javamodule.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fappy.javamodule.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findUserByUsername(String username);
	
	@Query("SELECT u FROM User u JOIN FETCH u.spaceSlots s WHERE s.familySpace.id = ?1 and s.urgenceContact = true and s.validationState = 'VALID'")
	List<User> findAllUsersBySpaceIdAndUrgenceContact(long spaceId);
	
}
