package com.fappy.javamodule.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fappy.javamodule.domain.entity.space.FamilySpace;
import com.fappy.javamodule.repository.FamilySpaceRepository;

@Service
public class FamilySpaceService {

	@Autowired
	private FamilySpaceRepository familySpaceRepository;
	
	/**
	 * Find a family space by id from the data base.
	 * @param id
	 * @return optional of family space
	 */
	public Optional<FamilySpace> findById(long id) {
		return this.familySpaceRepository.findById(id);
	}
	
	/**
	 * Find all the spaces from the data base.
	 * @return list of family spaces
	 */
	public List<FamilySpace> findAllFamilySpaces() {
		return this.familySpaceRepository.findAll();
	}

	/**
	 * Save a family space in the data base.
	 * @param familySpace
	 * @return family space
	 */
	public FamilySpace save(FamilySpace familySpace) {
		return this.familySpaceRepository.save(familySpace);
	}
}
