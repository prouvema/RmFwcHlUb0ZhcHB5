package com.fappy.javamodule.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fappy.javamodule.domain.entity.space.FamilyLink;
import com.fappy.javamodule.repository.FamilyLinkRepository;

@Service
public class ReferenceService {

	@Autowired
	private FamilyLinkRepository familyLinkRepository;
	
	public Optional<FamilyLink> findFamilyLinkById(long id) {
		return this.familyLinkRepository.findById(id);
	}
	
	public Optional<FamilyLink> findFamilyLinkByName(String name) {
		return this.familyLinkRepository.findFamilyLinkByName(name);
	}
	
	public List<FamilyLink> findAllFamilyLinks() {
		return this.familyLinkRepository.findAll();
	}
	
	public FamilyLink saveFamilyLink(FamilyLink familyLink) {
		return this.familyLinkRepository.save(familyLink);
	}
	
	public void deleteFamilyLink(FamilyLink familyLink) {
		this.familyLinkRepository.delete(familyLink);
	}

}
