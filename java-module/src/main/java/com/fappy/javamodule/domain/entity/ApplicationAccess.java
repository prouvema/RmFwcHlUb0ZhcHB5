package com.fappy.javamodule.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "application_access", uniqueConstraints={@UniqueConstraint(columnNames={"authority"})})
public class ApplicationAccess extends AbstractEntity implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8193403552359234914L;
	
	@NotNull
	@Size(max = 124)
	@Column(nullable = false,  length = 124)
	private String authority;

	@Override
	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
}
