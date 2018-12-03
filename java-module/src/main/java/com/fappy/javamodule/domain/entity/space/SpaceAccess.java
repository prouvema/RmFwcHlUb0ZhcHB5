package com.fappy.javamodule.domain.entity.space;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fappy.javamodule.domain.entity.AbstractEntity;

@Entity
@Table(name = "space_access")
public class SpaceAccess extends AbstractEntity {

	private String authority;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
