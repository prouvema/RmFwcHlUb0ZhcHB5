package com.fappy.javamodule.domain.entity.space;

import javax.persistence.Column;
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

	@Column(nullable = false)
	private String content;
	
	@JoinColumn(name = "family_space_id")
	@ManyToOne(optional = false)
	private FamilySpace familySpace;
	
	@OneToOne(optional = false)
	private User user;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public FamilySpace getFamilySpace() {
		return familySpace;
	}

	public void setFamilySpace(FamilySpace familySpace) {
		this.familySpace = familySpace;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
