package com.fappy.javamodule.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fappy.javamodule.domain.entity.space.SpaceSlot;
import com.fappy.javamodule.repository.SpaceSlotRepository;

@Service
public class SpaceSlotService {

	@Autowired
	private SpaceSlotRepository spaceSlotRepository;
	
	/**
	 * Find a space slot by id from the data base.
	 * @param id
	 * @return optional of space slot
	 */
	public Optional<SpaceSlot> findById(long id) {
		return this.spaceSlotRepository.findById(id);
	}
}
