package com.fappy.javamodule.domain.entity.space;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fappy.javamodule.domain.entity.AbstractEntity;

@Entity
@Table(name = "family_link")
public class FamilyLink extends AbstractEntity {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
