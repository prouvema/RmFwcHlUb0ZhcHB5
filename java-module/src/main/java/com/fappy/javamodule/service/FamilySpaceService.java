package com.fappy.javamodule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fappy.javamodule.domain.entity.space.FamilySpace;
import com.fappy.javamodule.repository.FamilySpaceRepository;

@Service
public class FamilySpaceService {

	@Autowired
	private FamilySpaceRepository familySpaceRepository;
	
	public List<FamilySpace> findAllFamilySpaces() {
		return this.familySpaceRepository.findAll();
	}
}
