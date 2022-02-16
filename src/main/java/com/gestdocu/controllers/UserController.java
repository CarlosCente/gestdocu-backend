package com.gestdocu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestdocu.auth.handler.UserAlreadyExistException;
import com.gestdocu.models.entity.Usuario;
import com.gestdocu.models.service.JpaUserDetailsService;

@RestController
public class UserController {

	@Autowired
    private JpaUserDetailsService userService;

    @PostMapping(value = "/api/user")
    public String register(@RequestBody Usuario newUser) {

	    if(newUser.getUsername() != null && newUser.getPassword() != null) {
	    	try {
				userService.register(newUser);
			} catch (UserAlreadyExistException e) {
				e.printStackTrace();
				return "Ya existe un usuario con ese nombre";
			}
	    	return "TODO OK";
	    } else {
	    	return "No se han recibido correctamente los datos del usuario";
	    }
	    
    }
    
    
    /*@PutMapping(value = "/api/user/deshabilitar")
    public String deshabilitarUsuario(@RequestBody Usuario usuario) {
    	
    	if (userService.checkIfUserExist(usuario.getUsername())) {
    		userService.deshabilitarUsuario(usuario);
    		return usuario.toString();
    	} else {
    		return "No existe el usuario";
    	}
    	
    }*/
	
}
