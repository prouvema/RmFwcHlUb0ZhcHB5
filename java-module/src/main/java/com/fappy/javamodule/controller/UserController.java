package com.fappy.javamodule.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fappy.javamodule.builder.dto.UrgenceContactDTOBuilder;
import com.fappy.javamodule.builder.dto.UserDTOBuilder;
import com.fappy.javamodule.domain.entity.User;
import com.fappy.javamodule.dto.UrgenceContactDTO;
import com.fappy.javamodule.dto.UserDTO;
import com.fappy.javamodule.service.UserService;

@RestController
@RequestMapping(path="/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	// TODO Améliorer avec query uniquement username
	/**
	 * 
	 * @return response with set String
	 */
	@GetMapping(path = "select")
	public ResponseEntity<Set<String>> findAllUsers_ForSelect() {
		List<User> users = this.userService.findAllUsers();
		Set<String> usernames = users.stream()
				.map(user -> user.getUsername())
				.collect(Collectors.toSet());
		return ResponseEntity.ok(usernames);
	}
	
	@GetMapping
	public ResponseEntity<Set<UserDTO>> findAllUsers() {
		List<User> users = this.userService.findAllUsers();
		Set<UserDTO> userDTOs = users.stream()
				.map(user -> new UserDTOBuilder() .withUser(user).build())
				.collect(Collectors.toSet());
		return ResponseEntity.ok(userDTOs);
	}
	
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

	@GetMapping(path = "{spaceId}/urgencecontacts")
	public ResponseEntity<List<UrgenceContactDTO>> findUrgenceContactsBySpaceId(@PathVariable long spaceId) {
		List<User> urgenceContacts = this.userService.findUrgenceContactsBySpaceId(spaceId);
		
		List<UrgenceContactDTO> urgenceContactDtos = urgenceContacts.stream()
				.map(user -> new UrgenceContactDTOBuilder().withUserAndSpaceId(user, spaceId).build())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(urgenceContactDtos);
	}
}
