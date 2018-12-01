package com.fappy.javamodule.config;

import java.util.Base64;

import org.springframework.security.crypto.password.PasswordEncoder;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/**
 * The Class LockerEncryption. Contains static methods to hash a string &
 * validate
 */
public class Argon2PasswordEncoder implements PasswordEncoder {

	/** The Constant ARGON2. */
	private static final Argon2 ARGON2 = Argon2Factory.create();

	/** The Constant ITERATIONS. */
	private static final int ITERATIONS = 1;

	/** The Constant MEMORY. */
	private static final int MEMORY = 65536;

	/** The Constant PARALLELISM. */
	private static final int PARALLELISM = 1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.crypto.password.PasswordEncoder#encode(java.lang
	 * .CharSequence)
	 */
	@Override
	public String encode(CharSequence rawPassword) {
		final String hash = ARGON2.hash(ITERATIONS, MEMORY, PARALLELISM, rawPassword.toString());
		return hash;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.crypto.password.PasswordEncoder#matches(java.
	 * lang.CharSequence, java.lang.String)
	 */
	@Override
	public boolean matches(CharSequence rawB64Password, String encodedPassword) {
		
		// Decode base64 password and replace = signs with nothing
		// (= signs are added to the end of base 64 string to padding)
		byte[] decodedBytes = Base64.getUrlDecoder().decode(rawB64Password.toString().replaceAll("=", ""));
		String rawpassword = "secret".equals(rawB64Password) ? "secret" : new String(decodedBytes);
		
		// return true if the password matches the hash, false otherwise.
		boolean match = ARGON2.verify(encodedPassword, rawpassword);
		return match;
	}

}
