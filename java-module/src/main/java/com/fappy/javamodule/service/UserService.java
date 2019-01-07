package com.fappy.javamodule.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fappy.javamodule.domain.entity.User;
import com.fappy.javamodule.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = this.userRepository.findUserByUsername(username);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
				user.get().getPassword(), user.get().isEnabled(), user.get().isAccountNonExpired(),
				user.get().isCredentialsNonExpired(), user.get().isAccountNonLocked(), user.get().getAuthorities());
	}
	
	/**
	 * Find the current logged user from the security context.
	 * 
	 * @return user
	 * @throws LMSException
	 */
	public User findLoggedUser() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return this.findUserByUsername(userDetails.getUsername()).get();
	}
	
	/**
	 * Find a user by his username equals to his email.
	 * @param username
	 * @return optional of user
	 */
	public Optional<User> findUserByUsername(String username) {
		return this.userRepository.findUserByUsername(username);
	}
	
	/**
	 * Find all the users from the database.
	 * @return list of users
	 */
	public List<User> findAllUsers() {
		return this.userRepository.findAll();
	}

	/**
	 * Find all the users urgence contact from a space.
	 * @param slotId
	 * @return list of user
	 */
	public List<User> findUrgenceContactsBySpaceId(long spaceId) {
		return this.userRepository.findAllUsersBySpaceIdAndUrgenceContact(spaceId);
	}
	
}
