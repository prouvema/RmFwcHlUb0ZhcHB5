package com.fappy.javamodule.domain.entity.space;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fappy.javamodule.domain.entity.AbstractEntity;

@Entity
@Table(name = "space_role")
public class SpaceRole extends AbstractEntity {

	private String name;
	
	@JoinTable(
			name = "space_role_space_access_join", 
			joinColumns = @JoinColumn(name = "space_role_id"), 
			inverseJoinColumns = @JoinColumn(name = "space_access_id")
			)
	@ManyToMany
	private Set<SpaceAccess> spaceAccesses;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<SpaceAccess> getSpaceAccesses() {
		return spaceAccesses;
	}

	public void setSpaceAccesses(Set<SpaceAccess> spaceAccesses) {
		this.spaceAccesses = spaceAccesses;
	}
}
