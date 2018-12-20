package com.fappy.javamodule.domain.entity.space;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fappy.javamodule.domain.entity.AbstractEntity;

@Entity
@Table(name = "space_access", uniqueConstraints={@UniqueConstraint(columnNames={"authority"})})
public class SpaceAccess extends AbstractEntity {

	@NotNull
	@Size(max = 128)
	@Column(nullable = false, length = 128)
	private String authority;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
