package com.fappy.javamodule.test.builder;

import com.fappy.javamodule.domain.entity.Profile;
import com.fappy.javamodule.domain.entity.User;

public class UserTestBuilder {

	private User user = new User();
	
	public User build() {
		return this.user;
	}
	
	public UserTestBuilder withNotNullField() {
		
		this.user.setUsername("test@test.test");
		this.user.setPassword("passwordTest");
		this.user.setProfile(new Profile());
		
		return this;
	}
	
}
