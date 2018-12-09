package com.fappy.javamodule.dto;

import java.util.Set;

public class FamilySpaceDTO {

	private long id;
	private String name;
	private Set<SpaceSlotDTO> spaceSlots;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
