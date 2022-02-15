package com.gestdocu.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.gestdocu.models.entity.Usuario;


public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	public Usuario findByUsername(String username);
	
	public Boolean existsByUsername(String username);
	
	
}
