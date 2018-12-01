package com.fappy.javamodule.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fappy.javamodule.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findUserByUsername(String username);
	
}
