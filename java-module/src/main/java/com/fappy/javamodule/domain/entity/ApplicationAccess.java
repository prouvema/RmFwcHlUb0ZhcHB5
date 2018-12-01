package com.fappy.javamodule.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "application_access")
public class ApplicationAccess extends AbstractEntity implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8193403552359234914L;
	
	private String authority;

	@Override
	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
}
