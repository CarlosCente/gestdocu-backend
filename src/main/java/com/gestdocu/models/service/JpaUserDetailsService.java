package com.gestdocu.models.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestdocu.auth.handler.UserAlreadyExistException;
import com.gestdocu.models.dao.IUsuarioDao;
import com.gestdocu.models.entity.Role;
import com.gestdocu.models.entity.Usuario;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private IUsuarioDao usuarioDao;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioDao.findByUsername(username);

		if (usuario == null) {
			logger.error("Error en el Login: no existe el usuario '" + username + "' en el sistema!");
			throw new UsernameNotFoundException("Username: " + username + " no existe en el sistema!");
		}

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Role role : usuario.getRoles()) {
			logger.info("Role: ".concat(role.getAuthority()));
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}

		if (authorities.isEmpty()) {
			logger.error("Error en el Login: Usuario '" + username + "' no tiene roles asignados!");
			throw new UsernameNotFoundException(
					"Error en el Login: usuario '" + username + "' no tiene roles asignados!");
		}

		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
				authorities);
	}

	/**
	 * Método para registrar un usuario en la base de datos
	 * @param user usuario a registrar
	 * @throws UserAlreadyExistException
	 */
	public void register(Usuario user) throws UserAlreadyExistException {

		if (checkIfUserExist(user.getUsername())) {
			throw new UserAlreadyExistException("Ya existe un usuario con ese mismo nombre");
		}
		Usuario usuario = new Usuario();
		BeanUtils.copyProperties(user, usuario);
		encodePassword(usuario, user);
		
		//La fecha de ultima modificacion coincidira con la de creación en el registro
		Date fechaActual = new Date();
		usuario.setUltimaModificacion(fechaActual);
		usuarioDao.save(usuario);
	}
	
	/*public void deshabilitarUsuario(Usuario user) {
		if (checkIfUserExist(user.getUsername())) {
			Usuario userSave = usuarioDao.findByUsername(user.getUsername());
			userSave.setUltimaModificacion(new Date());
			userSave.setEnabled(false);
			usuarioDao.save(userSave);
		} else {
			//TO DO
			//CONTROLAR CUANDO NO EXISTE EL USUARIO, NUEVO ERROR
		}
	}*/

	
	/**
	 * Método para comprobar si existe un usuario mediante su username
	 * @param username
	 * @return
	 */
	public boolean checkIfUserExist(String username) {
		return usuarioDao.existsByUsername(username);
	}
	
	/**
	 * Método para comprobar si el usuario se encuentra habilitado
	 * @param username
	 * @return
	 */
	public boolean checkIfUserEnable(String username) {
		Usuario user = usuarioDao.findByUsername(username);
		
		if (user != null) {
			return user.getEnabled();	
		} 		
		
		return false;
	}
	
	private void encodePassword(Usuario userEntity, Usuario user) {
		userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
	}

}
