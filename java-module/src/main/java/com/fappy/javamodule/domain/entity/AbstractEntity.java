package com.fappy.javamodule.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@MappedSuperclass
public abstract class AbstractEntity {

	/** The auto-generated id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/** The create time. */
	@Column(name = "create_time", nullable = false, updatable = false)
	private LocalDateTime createTime;

	/** The create user. */
	@Column(name = "create_user", nullable = false, updatable = false)
	private String createUser;

	/** The update time. */
	@Column(name = "update_time", nullable = false)
	private LocalDateTime updateTime;

	/** The update user. */
	@Column(name = "update_user", nullable = false)
	private String updateUser;

	/** The version number. */
	@Version
	@Column(name = "version_number", nullable = false)
	private Long versionNumber;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;
	
	@PrePersist
	public void setCreatedOn() {
		this.createTime = LocalDateTime.now();
		this.updateTime = LocalDateTime.now();
		String userName = "No Context";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (null != auth && !(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			userName = userDetails.getUsername();
		}
		this.createUser = userName;
		this.updateUser = userName;
	}

	/**
	 * Sets the updadted on.
	 *
	 * @param auditable
	 *            the new updadted on
	 */
	@PreUpdate
	public void setUpdadtedOn() {
		this.updateTime = LocalDateTime.now();
		String userName = "No Context";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			if (auth != null) {
				UserDetails userDetails = (UserDetails) auth.getPrincipal();
				userName = userDetails.getUsername();
			}
		}
		this.updateUser = userName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}
	
}
