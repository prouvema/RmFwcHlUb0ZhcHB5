package com.fappy.javamodule.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fappy.javamodule.config.Argon2PasswordEncoder;

public class LockerEncryptionTest {

	@Test
	public void should_return_a_correct_hash() {
		CharSequence rawPassword = "secret";
		
		Argon2PasswordEncoder lockerEncryption = new Argon2PasswordEncoder();
		String encodePassword = lockerEncryption.encode(rawPassword);
		
		System.out.println("encodePassword > " + encodePassword);
		
		boolean actual = lockerEncryption.matches("secret", encodePassword);
//		CharSequence rawPassword = "admin";
//		
//		Argon2PasswordEncoder lockerEncryption = new Argon2PasswordEncoder();
//		String encodePassword = lockerEncryption.encode(rawPassword);
//		
//		System.out.println("encodePassword > " + encodePassword);
//		
//		boolean actual = lockerEncryption.matches("YWRtaW4=", encodePassword);
		
		assertTrue(actual);
	}
}
