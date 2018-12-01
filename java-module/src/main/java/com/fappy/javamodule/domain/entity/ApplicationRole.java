package com.fappy.javamodule.domain.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "application_role")
public class ApplicationRole extends AbstractEntity {

	private String name;
	
	@JoinTable(
			name = "application_role_application_access_join", 
			joinColumns = @JoinColumn(name = "application_role_id"), 
			inverseJoinColumns = @JoinColumn(name = "application_access_id")
			)
	@ManyToMany
	private Set<ApplicationAccess> applicationAccesses;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ApplicationAccess> getApplicationAccesses() {
		return applicationAccesses;
	}

	public void setApplicationAccesses(Set<ApplicationAccess> applicationAccesses) {
		this.applicationAccesses = applicationAccesses;
	}
	
}
