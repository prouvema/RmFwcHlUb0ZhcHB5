package com.fappy.javamodule.domain.entity.space;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fappy.javamodule.domain.entity.AbstractEntity;

@Entity
@Table(name = "family_space")
public class FamilySpace extends AbstractEntity {

	@OneToMany(mappedBy = "familySpace", cascade = CascadeType.ALL)
	private Set<SpaceSlot> spaceSlots;
	
	@OneToMany(mappedBy = "familySpace")
	private Set<SpaceMessage> spaceMessages;
	
}
