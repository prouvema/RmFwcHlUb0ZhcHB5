package com.fappy.javamodule.domain.entity.space;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fappy.javamodule.domain.entity.AbstractEntity;

@Entity
@Table(name = "family_link", uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class FamilyLink extends AbstractEntity {

	@NotNull
	@Size(max = 64)
	@Column(nullable = false, length = 64)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
