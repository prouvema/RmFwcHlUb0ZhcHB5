package com.fappy.javamodule.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fappy.javamodule.builder.FamilySpaceDTOBuilder;
import com.fappy.javamodule.domain.entity.space.FamilySpace;
import com.fappy.javamodule.dto.FamilySpaceDTO;
import com.fappy.javamodule.service.FamilySpaceService;

@RestController
@RequestMapping(path="/api/spaces")
public class FamilySpaceController {

	@Autowired
	private FamilySpaceService familySpaceService;
	
	@GetMapping
	public ResponseEntity<Set<FamilySpaceDTO>> findAllFamilySpaces() {
		List<FamilySpace> familySpaces = this.familySpaceService.findAllFamilySpaces();
		
		Set<FamilySpaceDTO> familySpaceDTOs = familySpaces.stream()
				.map(familySpace -> new FamilySpaceDTOBuilder().withFamilySpace(familySpace).lightBuild())
				.collect(Collectors.toSet());
		
		return new ResponseEntity<>(familySpaceDTOs, HttpStatus.OK);
	}
	
}
