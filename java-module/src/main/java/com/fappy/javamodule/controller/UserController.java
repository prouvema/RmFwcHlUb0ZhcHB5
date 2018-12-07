package com.fappy.javamodule.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fappy.javamodule.builder.UserDTOBuilder;
import com.fappy.javamodule.domain.entity.User;
import com.fappy.javamodule.dto.UserDTO;
import com.fappy.javamodule.service.UserService;

@RestController
@RequestMapping(path="/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(path="current")
	public ResponseEntity<?> findCurrentUser() {
		User currentUser = this.userService.findLoggedUser();
		UserDTO userDTO = new UserDTOBuilder()
				.withUser(currentUser)
				.build();
		return ResponseEntity.ok(userDTO);
	}
	
	@GetMapping(path="accesses")
	public ResponseEntity<Set<String>> findAccesses() {
		User currentUser = this.userService.findLoggedUser();
		
		Set<String> accesses = new HashSet<>();
		currentUser.getApplicationRoles().stream()
			.forEach(role -> {
				role.getApplicationAccesses().stream()
					.map(access -> access.getAuthority())
					.filter(accessName -> !accesses.contains(accessName))
					.forEach(accessName -> accesses.add(accessName));
			});
			
		
		return ResponseEntity.ok(accesses);
	}
	
}
