package com.fappy.javamodule.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fappy.javamodule.builder.dto.SpaceSlotDTOBuilder;
import com.fappy.javamodule.domain.entity.space.SpaceSlot;
import com.fappy.javamodule.dto.SpaceSlotDTO;
import com.fappy.javamodule.service.SpaceSlotService;

@RestController
@RequestMapping(path="/api/spaceslots")
public class SpaceSlotController {

	@Autowired
	private SpaceSlotService spaceSlotService;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(path = "{id}")
	public ResponseEntity<?> findSpaceSlotById(@PathVariable long id) {
		Optional<SpaceSlot> slot = this.spaceSlotService.findById(id);
		if (!slot.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		SpaceSlotDTO spaceSlotDTO = new SpaceSlotDTOBuilder()
				.withSpaceSlot(slot.get())
				.build();
		
		return new ResponseEntity<>(spaceSlotDTO, HttpStatus.OK);
	}
	
}
