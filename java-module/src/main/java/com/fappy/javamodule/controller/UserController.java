package com.fappy.javamodule.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/users")
public class UserController {

	@GetMapping(path="hello")
	public ResponseEntity<?> helloPrivate() {
		return ResponseEntity.ok("private");
	}
	
}
