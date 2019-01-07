package com.fappy.javamodule.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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
	
	@GetMapping(path = "page/{page}/size/{size}")
	public ResponseEntity<Page<UserDTO>> findByPagination(@PathVariable int page, @PathVariable int size) {
		Pageable pageRequest = PageRequest.of(page, size, Sort.Direction.DESC, "createTime");
		Page<User> usersPage = userService.findAll(pageRequest);
		Page<UserDTO> dtoPage = usersPage.map(user -> new UserDTOBuilder().withUser(user).lightBuild());
		return ResponseEntity.ok(dtoPage);
	}
	
	@GetMapping(path = "{username}")
	public ResponseEntity<UserDTO> findByUsername(@PathVariable String username) {
		Optional<User> user = userService.findByUsername(username);
		if (!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		UserDTO userDTO = new UserDTOBuilder()
				.withUser(user.get())
				.build();
		return ResponseEntity.ok(userDTO);
	}
	
	// TODO Améliorer avec query uniquement username
	/**
	 * 
	 * @return response with set String
	 */
	@GetMapping(path = "select")
	public ResponseEntity<Set<String>> findAll_ForSelect() {
		List<User> users = this.userService.findAllUsers();
		Set<String> usernames = users.stream()
				.map(user -> user.getUsername())
				.collect(Collectors.toSet());
		return ResponseEntity.ok(usernames);
	}
	
	@GetMapping
	public ResponseEntity<Set<UserDTO>> findAll() {
		List<User> users = this.userService.findAllUsers();
		Set<UserDTO> userDTOs = users.stream()
				.map(user -> new UserDTOBuilder().withUser(user).build())
				.collect(Collectors.toSet());
		return ResponseEntity.ok(userDTOs);
	}
	
	@GetMapping(path="current")
	public ResponseEntity<?> findCurrent() {
		User currentUser = this.userService.findLoggedUser();
		if (null == currentUser) {
			return new ResponseEntity<String>("No current user", HttpStatus.NOT_FOUND);
		}
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
