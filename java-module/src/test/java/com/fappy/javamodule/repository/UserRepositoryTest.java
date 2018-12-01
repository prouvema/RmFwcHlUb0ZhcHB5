package com.fappy.javamodule.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fappy.javamodule.domain.entity.User;
import com.fappy.javamodule.test.builder.UserTestBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void should_succeed_the_crud_operation() {
		User user = new UserTestBuilder().withNotNullField().build();
		User userSaved = this.userRepository.save(user);
		
		assertNotNull(userSaved);
		assertTrue(userSaved.getId() > 0);
		assertNotEquals("titi", userSaved.getProfile().getLastname());
		
		Optional<User> userReaded = this.userRepository.findById(userSaved.getId());
		
		assertTrue(userReaded.isPresent());
		
		userSaved.getProfile().setLastname("titi");
		User userUpdated = this.userRepository.save(userSaved);
		
		assertNotNull(userUpdated);
		assertEquals("titi", userSaved.getProfile().getLastname());
		
		this.userRepository.delete(userUpdated);
		Optional<User> userDeleted = this.userRepository.findById(userUpdated.getId());
		
		assertFalse(userDeleted.isPresent());
	}
}
