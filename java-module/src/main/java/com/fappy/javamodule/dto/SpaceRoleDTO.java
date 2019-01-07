package com.fappy.javamodule.dto;

import java.util.ArrayList;
import java.util.List;

public class SpaceRoleDTO extends AbstractDTO {

	private String name;
	private List<String> accesses = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getAccesses() {
		return accesses;
	}
	public void setAccesses(List<String> accesses) {
		this.accesses = accesses;
	}
	
}
