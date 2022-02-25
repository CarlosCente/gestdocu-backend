package com.gestdocu.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gestdocu.dao.RoleRepository;
import com.gestdocu.dao.UserRepository;
import com.gestdocu.model.AuthResponse;
import com.gestdocu.model.Role;
import com.gestdocu.model.Roles;
import com.gestdocu.model.SignupRequest;
import com.gestdocu.model.User;
import com.gestdocu.security.JwtTokenUtil;

@RestController
@CrossOrigin(origins="http://localhost:4200") 
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtTokenUtil jwtTokenUtil;
		
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		user.getUserName(),
                		user.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
      
        //Se obtienen los roles del usuario para añadirlos a la respuesta 
        //TO DO crear método que haga esto controlando varias cosas
        User usuario = userRepository.findByUserName(user.getUserName()).get();
        Set<Role> rolesUsuario = usuario.getRoles();
        List<String> lRolesUsu = new ArrayList<String>();
        for (Role role: rolesUsuario) {
        	lRolesUsu.add(role.getRoleName().toString());
        }
        
        String jwt = jwtTokenUtil.generateJwtToken(authentication);
        return ResponseEntity.ok(new AuthResponse(jwt, lRolesUsu));
    }
	
    /*
     * Este método posteriormente solo podra ser llamado por un usuario administrador que se ocupara de registrar usuarios relacionados con un cliente o empresa
     */
	@PostMapping("/signup")
	public ResponseEntity<?> userSignup(@Valid @RequestBody SignupRequest signupRequest) {
		if(userRepository.existsByUserName(signupRequest.getUserName())){
			return ResponseEntity.badRequest().body("Username is already taken");
		}
		if(userRepository.existsByEmail(signupRequest.getEmail())){
			return ResponseEntity.badRequest().body("Email is already taken");
		}
		User user = new User();
		Set<Role> roles = new HashSet<>();
		user.setUserName(signupRequest.getUserName());
		user.setEmail(signupRequest.getEmail());
		user.setPassword(encoder.encode(signupRequest.getPassword()));
		//System.out.println("Encoded password--- " + user.getPassword());
		String[] roleArr = signupRequest.getRoles();
		
		if(roleArr == null) {
			roles.add(roleRepository.findByRoleName(Roles.ROLE_USER).get());
		}
		for(String role: roleArr) {
			switch(role.toLowerCase()) {
				case "admin":
					roles.add(roleRepository.findByRoleName(Roles.ROLE_ADMIN).get());
					break;
				case "user":
					roles.add(roleRepository.findByRoleName(Roles.ROLE_USER).get());
					break;	
				default:
					return ResponseEntity.badRequest().body("Specified role not found");
			}
		}
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok("User signed up successfully");
	}
}
