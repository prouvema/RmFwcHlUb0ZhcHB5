package com.fappy.javamodule.dto;

import java.util.Set;

public class FamilySpaceDTO extends AbstractDTO {

	private String name;
	private Set<SpaceSlotDTO> spaceSlots;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<SpaceSlotDTO> getSpaceSlots() {
		return spaceSlots;
	}
	public void setSpaceSlots(Set<SpaceSlotDTO> spaceSlots) {
		this.spaceSlots = spaceSlots;
	}
	
}
