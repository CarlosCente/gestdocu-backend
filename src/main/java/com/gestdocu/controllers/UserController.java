package com.gestdocu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestdocu.models.entity.Usuario;
import com.gestdocu.models.service.JpaUserDetailsService;

@RestController
public class UserController {

	@Autowired
    private JpaUserDetailsService userService;

    @PostMapping(value = "/api/user/registro")
    public String register(@RequestBody Usuario newUser){

    
       return "HEMOS LLEGADO AL REGISTRO";
    }
	
}
