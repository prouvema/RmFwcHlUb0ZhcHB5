package com.fappy.javamodule.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fappy.javamodule.builder.dto.FamilySpaceDTOBuilder;
import com.fappy.javamodule.builder.entity.FamilySpaceBuilder;
import com.fappy.javamodule.domain.entity.space.FamilySpace;
import com.fappy.javamodule.dto.FamilySpaceDTO;
import com.fappy.javamodule.service.FamilySpaceService;
import com.fappy.javamodule.service.ReferenceService;
import com.fappy.javamodule.service.SpaceRoleService;
import com.fappy.javamodule.service.UserService;
import com.fappy.javamodule.updator.FamilySpaceUpdator;

@RestController
@RequestMapping(path="/api/spaces")
public class FamilySpaceController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ReferenceService referenceService;
	
	@Autowired
	private FamilySpaceService familySpaceService;
	
	@Autowired
	private SpaceRoleService spaceRoleService;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(path = "{id}")
	public ResponseEntity<?> findById(@PathVariable long id) {
		Optional<FamilySpace> familySpace = this.familySpaceService.findById(id);
		if (!familySpace.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		FamilySpaceDTO familySpaceDTO = new FamilySpaceDTOBuilder()
				.withFamilySpace(familySpace.get())
				.build();
		
		return new ResponseEntity<>(familySpaceDTO, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<Set<FamilySpaceDTO>> findAll() {
		List<FamilySpace> familySpaces = this.familySpaceService.findAllFamilySpaces();
		
		Set<FamilySpaceDTO> familySpaceDTOs = familySpaces.stream()
				.map(familySpace -> new FamilySpaceDTOBuilder().withFamilySpace(familySpace).lightBuild())
				.collect(Collectors.toSet());
		
		return new ResponseEntity<>(familySpaceDTOs, HttpStatus.OK);
	}

	@GetMapping(path = "page/{page}/size/{size}")
	public ResponseEntity<Page<FamilySpaceDTO>> findByPagination(@PathVariable int page, @PathVariable int size) {
		Pageable pageRequest = PageRequest.of(page, size, Sort.Direction.DESC, "createTime");
		Page<FamilySpace> spacesPage = this.familySpaceService.findAll(pageRequest);
		Page<FamilySpaceDTO> dtoPage = spacesPage.map(familySpace -> new FamilySpaceDTOBuilder().withFamilySpace(familySpace).lightBuild());
		return ResponseEntity.ok(dtoPage);
	}
	
	/**
	 * 
	 * @param familySpaceDTO
	 * @return
	 */
	@PatchMapping
	public ResponseEntity<?> update(@RequestBody FamilySpaceDTO familySpaceDTO) {
		
		Optional<FamilySpace> familySpaceRead = this.familySpaceService.findById(familySpaceDTO.getId());
		if (!familySpaceRead.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		FamilySpace familySpace = new FamilySpaceUpdator(familySpaceRead.get(), 
														this.referenceService, 
														this.userService, 
														this.spaceRoleService)
				.withFamilySpaceDTO(familySpaceDTO)
				.update();
		
		FamilySpace familySpaceSaved = this.familySpaceService.save(familySpace);
		
		FamilySpaceDTO familySpaceDTOSaved = new FamilySpaceDTOBuilder().withFamilySpace(familySpaceSaved).build();
		
		return new ResponseEntity<>(familySpaceDTOSaved, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param familySpaceDTO
	 * @return
	 */
	@PostMapping
	public ResponseEntity<FamilySpaceDTO> create(@RequestBody FamilySpaceDTO familySpaceDTO) {
		
		FamilySpace familySpace = new FamilySpaceBuilder(this.referenceService, this.userService, this.spaceRoleService)
				.withFamilySpaceDTO(familySpaceDTO)
				.build();

		FamilySpace familySpaceSaved = this.familySpaceService.save(familySpace);
		
		FamilySpaceDTO familySpaceDTOSaved = new FamilySpaceDTOBuilder().withFamilySpace(familySpaceSaved).build();
		
		return new ResponseEntity<>(familySpaceDTOSaved, HttpStatus.CREATED);
	}
	
	//----------------------------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------------------------

}
