package com.fappy.javamodule.controller;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fappy.javamodule.domain.entity.space.FamilyLink;
import com.fappy.javamodule.service.ReferenceService;

@RestController
@RequestMapping(path="/api/references")
public class ReferenceController {

	@Autowired
	private ReferenceService referenceService;
	
	@GetMapping(path = "familylinks")
	public ResponseEntity<Set<String>> findAllFamilyLinks() {
		Set<String> familyLinks = this.referenceService.findAllFamilyLinks().stream()
			.map(link -> link.getName())
			.collect(Collectors.toSet());
		
		return new ResponseEntity<Set<String>>(familyLinks, HttpStatus.OK);
	}
	
	@PostMapping(path = "familylinks")
	public ResponseEntity<String> postFamilyLink(@RequestBody String familyLink) {
		
		if (this.referenceService.findFamilyLinkByName(familyLink).isPresent()) {
			return new ResponseEntity<>("Family link with name " + familyLink + " already exists", HttpStatus.CONFLICT);
		}
		
		FamilyLink link = new FamilyLink();
		link.setName(familyLink);
		
		FamilyLink savedLink = this.referenceService.saveFamilyLink(link);
		
		return new ResponseEntity<String>("\""+savedLink.getName()+"\"", HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "familylinks/{linkName}")
	public ResponseEntity<?> deleteFamilyLink(@PathVariable String linkName) {
		Optional<FamilyLink> familyLink = this.referenceService.findFamilyLinkByName(linkName);
		if (familyLink.isPresent()) {
			this.referenceService.deleteFamilyLink(familyLink.get());
		}
		return ResponseEntity.ok().build();
	}
	
}
