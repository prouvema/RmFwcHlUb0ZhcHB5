package com.fappy.javamodule.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
}
