package com.fappy.javamodule.domain.entity.space;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fappy.javamodule.domain.entity.AbstractEntity;
import com.fappy.javamodule.domain.entity.User;

@Entity
@Table(name = "space_message")
public class SpaceMessage extends AbstractEntity {

	@JoinColumn(name = "family_space_id")
	@ManyToOne(optional = false)
	private FamilySpace familySpace;
	
	@OneToOne(optional = false)
	private User user;
	
}
