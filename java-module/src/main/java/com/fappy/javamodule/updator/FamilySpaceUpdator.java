package com.fappy.javamodule.updator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fappy.javamodule.builder.entity.SpaceSlotBuilder;
import com.fappy.javamodule.domain.entity.space.FamilySpace;
import com.fappy.javamodule.domain.entity.space.SpaceSlot;
import com.fappy.javamodule.dto.FamilySpaceDTO;
import com.fappy.javamodule.dto.SpaceSlotDTO;
import com.fappy.javamodule.service.ReferenceService;
import com.fappy.javamodule.service.SpaceRoleService;
import com.fappy.javamodule.service.UserService;
import com.fappy.javamodule.utils.CollectionUtils;

public class FamilySpaceUpdator {
	
	private UserService userService;
	private ReferenceService referenceService;
	private FamilySpace familySpace;
	private SpaceRoleService spaceRoleService;
	
	public FamilySpaceUpdator(
			FamilySpace familySpace, 
			ReferenceService referenceService, 
			UserService userService,
			SpaceRoleService spaceRoleService) 
	{
		super();
		this.userService = userService;
		this.referenceService = referenceService;
		this.familySpace = familySpace;
		this.spaceRoleService = spaceRoleService;
	}

	/**
	 * 
	 * @return
	 */
	public FamilySpace update() {
		return this.familySpace;
	}
	
	/**
	 * 
	 * @param dto
	 * @return
	 */
	public FamilySpaceUpdator withFamilySpaceDTO(FamilySpaceDTO dto) {
		this.familySpace.setName(dto.getName());
		this.deleteOldSpaceSlots(dto.getSpaceSlots());
		this.updateOrAddSpaceSlots(dto);
		return this;
	}
	
	/**
	 * 
	 * @param spaceSlots
	 */
	private void deleteOldSpaceSlots(Set<SpaceSlotDTO> spaceSlots) {
		List<Long> slotDtoIds = spaceSlots.stream()
				.map(SpaceSlotDTO::getId)
				.collect(Collectors.toList());
		List<SpaceSlot> slotsToDelete = new ArrayList<>();
		
		this.familySpace.getSpaceSlots().stream()
			.filter(slot -> !CollectionUtils.isIdExistsInIds(slot.getId(), slotDtoIds))
			.forEach(slot -> slotsToDelete.add(slot));
		
		this.familySpace.getSpaceSlots().removeAll(slotsToDelete);
	}

	/**
	 * 
	 * @param dto
	 */
	private void updateOrAddSpaceSlots(FamilySpaceDTO dto) {
		List<Long> slotIds = this.familySpace.getSpaceSlots().stream()
				.map(SpaceSlot::getId)
				.collect(Collectors.toList());
		
		dto.getSpaceSlots().forEach(slotDto -> {
			if (CollectionUtils.isIdExistsInIds(slotDto.getId(), slotIds)) {
				
				int slotIndex = CollectionUtils.findIndexOfIdInIds(slotDto.getId(), slotIds);
				SpaceSlot slotToUpdate = (SpaceSlot) this.familySpace.getSpaceSlots().toArray()[slotIndex];
				new SpaceSlotUpdator(slotToUpdate, this.userService, this.spaceRoleService, this.referenceService)
					.withSpaceSlotDTO(slotDto)
					.update();
				
			} else {
				this.addSpaceSlotToFamilySpaceWithDto(slotDto);
			}
		});
	}

	/**
	 * 
	 * @param slotDto
	 */
	private void addSpaceSlotToFamilySpaceWithDto(SpaceSlotDTO slotDto) {
		SpaceSlot newSpaceSlot = new SpaceSlotBuilder(this.referenceService, this.userService, this.spaceRoleService)
				.withSpaceSlotDTO(slotDto)
				.build(this.familySpace);
		this.familySpace.getSpaceSlots().add(newSpaceSlot);
	}

}
