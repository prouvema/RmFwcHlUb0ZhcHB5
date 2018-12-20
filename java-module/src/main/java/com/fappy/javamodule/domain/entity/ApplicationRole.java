package com.fappy.javamodule.domain.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "application_role", uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class ApplicationRole extends AbstractEntity {

	@NotNull
	@Size(max = 124)
	@Column(nullable = false, length = 124)
	private String name;
	
	@JoinTable(
			name = "application_role_application_access_join", 
			joinColumns = @JoinColumn(name = "application_role_id"), 
			inverseJoinColumns = @JoinColumn(name = "application_access_id")
			)
	@ManyToMany(fetch = FetchType.EAGER)
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
