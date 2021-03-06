package com.fappy.javamodule.domain.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fappy.javamodule.domain.entity.space.SpaceSlot;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class User extends AbstractEntity implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 88958501666776965L;

	@NotNull
	@Size(max = 191)
	@Column(name = "email", nullable = false, length = 191)
    private String username;
	
	@JsonIgnore
	@NotNull
	@Column(nullable = false)
	private String password;
	
	@NotNull
	@Column(nullable = false)
	private boolean enabled = false;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Profile profile;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<SpaceSlot> spaceSlots;
	
	@JoinTable(
			name = "user_application_role_join", 
			joinColumns = @JoinColumn(name = "user_id"), 
			inverseJoinColumns = @JoinColumn(name = "application_role_id")
			)
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<ApplicationRole> applicationRoles;
	
	@NotNull
	@Column(name = "account_non_expired", nullable = false)
	private boolean accountNonExpired = true;

	@NotNull
	@Column(name = "account_non_locked", nullable = false)
	private boolean accountNonLocked = true;
	
	@NotNull
	@Column(name = "credentials_non_expired", nullable = false)
	private boolean credentialsNonExpired = true;
	
	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<ApplicationAccess> applicationAccesses = new HashSet<>();
		
		this.applicationRoles.stream().forEach(applicationRole -> {
			applicationRole.getApplicationAccesses().stream()
				.filter(applicationAccess -> !applicationAccesses.contains(applicationAccess))
				.forEach(applicationAccess -> applicationAccesses.add(applicationAccess));
		});
		
		return applicationAccesses;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Set<ApplicationRole> getApplicationRoles() {
		return applicationRoles;
	}

	public void setApplicationRoles(Set<ApplicationRole> applicationRoles) {
		this.applicationRoles = applicationRoles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public Set<SpaceSlot> getSpaceSlots() {
		return spaceSlots;
	}

	public void setSpaceSlots(Set<SpaceSlot> slotSpaces) {
		this.spaceSlots = slotSpaces;
	}
    
}
