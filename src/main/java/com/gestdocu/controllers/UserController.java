package com.gestdocu.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestdocu.dao.UserRepository;
import com.gestdocu.model.User;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/usuarios")
	public List<User> allUsers() {
		System.out.println("UserController -- userLogin");
		return userRepository.findAll();
	}	
	
	@GetMapping("/usuario/{username}")
	public Optional<User> getUser(@PathVariable String username) {
		System.out.println("UserController -- getUser");
		return userRepository.findByUsername(username);
	}
	
}
