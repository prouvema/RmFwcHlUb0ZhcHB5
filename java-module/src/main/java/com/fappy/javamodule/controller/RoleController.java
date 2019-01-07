package com.fappy.javamodule.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fappy.javamodule.builder.dto.SpaceRoleDTOBuilder;
import com.fappy.javamodule.builder.entity.SpaceRoleBuilder;
import com.fappy.javamodule.domain.entity.space.SpaceRole;
import com.fappy.javamodule.dto.SpaceRoleDTO;
import com.fappy.javamodule.service.SpaceRoleService;
import com.fappy.javamodule.updator.SpaceRoleUpdator;

@RestController
@RequestMapping(path="/api/roles")
public class RoleController {

	@Autowired
	private SpaceRoleService spaceRoleService;
	
	@GetMapping(path = "space")
	public ResponseEntity<List<SpaceRoleDTO>> findAllSpaceRoles() {
		List<SpaceRole> spaceRoles = this.spaceRoleService.findAllSpaceRoles();
		List<SpaceRoleDTO> spaceRoleDTOs = spaceRoles.stream()
												.map(spaceRole -> new SpaceRoleDTOBuilder()
																		.withSpaceRole(spaceRole)
																		.build())
												.collect(Collectors.toList());
		return new ResponseEntity<List<SpaceRoleDTO>>(spaceRoleDTOs, HttpStatus.OK);
	}
	
	@GetMapping(path = "space/select")
	public ResponseEntity<Set<String>> findAllSpaceRoles_ForDisplay() {
		Set<String> spaceRoles = this.spaceRoleService.findAllSpaceRoles().stream()
			.map(role -> role.getName())
			.collect(Collectors.toSet());
		
		return new ResponseEntity<Set<String>>(spaceRoles, HttpStatus.OK);
	}
	
	@PostMapping(path = "space")
	public ResponseEntity<?> postSpaceRole(@RequestBody SpaceRoleDTO spaceRoleDTO) {
		if (this.spaceRoleService.findSpaceRoleByName(spaceRoleDTO.getName()).isPresent()) {
			return new ResponseEntity<String>("Space role with name " + spaceRoleDTO.getName() + " already exists", HttpStatus.CONFLICT);
		}
		SpaceRole spaceRole = new SpaceRoleBuilder(this.spaceRoleService)
									.withSpaceRoleDto(spaceRoleDTO)
									.build();
		SpaceRole savedSpaceRole = this.spaceRoleService.saveSpaceRole(spaceRole);
		SpaceRoleDTO savedSpaceRoleDTO = new SpaceRoleDTOBuilder()
												.withSpaceRole(savedSpaceRole)
												.build();
		return new ResponseEntity<SpaceRoleDTO>(savedSpaceRoleDTO, HttpStatus.OK);
	}
	
	@PatchMapping(path = "space")
	public ResponseEntity<?> patchSpaceRole(@RequestBody SpaceRoleDTO spaceRoleDTO) {
		Optional<SpaceRole> spaceRole = this.spaceRoleService.findById(spaceRoleDTO.getId());
		if (!spaceRole.isPresent()) {
			return new ResponseEntity<String>("Space role with id > " + spaceRoleDTO.getId() + " not found", HttpStatus.NOT_FOUND);
		}
		SpaceRole updatedSpaceRole = new SpaceRoleUpdator(this.spaceRoleService, spaceRole.get())
											.withSpaceRoleDto(spaceRoleDTO)
											.update();
		SpaceRole savedSpaceRole = this.spaceRoleService.saveSpaceRole(updatedSpaceRole);
		SpaceRoleDTO savedSpaceRoleDTO = new SpaceRoleDTOBuilder()
												.withSpaceRole(savedSpaceRole)
												.build();
		return new ResponseEntity<SpaceRoleDTO>(savedSpaceRoleDTO, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "space")
	public ResponseEntity<?> patchSpaceRole(@PathVariable long id) {
		this.spaceRoleService.deleteSpaceRoleById(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(path = "access")
	public ResponseEntity<Set<String>> findAllSpaceAccesses_ForDisplay() {
		Set<String> spaceAccesses = this.spaceRoleService.findAllSpaceAccesses().stream()
			.map(access -> access.getAuthority())
			.collect(Collectors.toSet());
		return new ResponseEntity<Set<String>>(spaceAccesses, HttpStatus.OK);
	}
	
}
