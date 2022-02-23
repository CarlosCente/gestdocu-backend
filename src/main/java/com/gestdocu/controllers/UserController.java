package com.gestdocu.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
}
