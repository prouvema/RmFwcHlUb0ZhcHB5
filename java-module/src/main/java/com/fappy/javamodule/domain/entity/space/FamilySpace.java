package com.fappy.javamodule.domain.entity.space;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fappy.javamodule.domain.entity.AbstractEntity;

@Entity
@Table(name = "family_space")
public class FamilySpace extends AbstractEntity {

	@NotNull
	@Size(max = 128)
	@Column(nullable = false, length = 128)
	private String name;
	
	@OneToMany(mappedBy = "familySpace", cascade = CascadeType.ALL)
	private Set<SpaceSlot> spaceSlots;
	
	@OneToMany(mappedBy = "familySpace")
	private Set<SpaceMessage> spaceMessages;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<SpaceSlot> getSpaceSlots() {
		return spaceSlots;
	}

	public void setSpaceSlots(Set<SpaceSlot> spaceSlots) {
		this.spaceSlots = spaceSlots;
	}

	public Set<SpaceMessage> getSpaceMessages() {
		return spaceMessages;
	}

	public void setSpaceMessages(Set<SpaceMessage> spaceMessages) {
		this.spaceMessages = spaceMessages;
	}
	
}
