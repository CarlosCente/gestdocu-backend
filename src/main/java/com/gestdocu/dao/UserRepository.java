package com.gestdocu.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestdocu.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	public Optional<User> findByUsername(String username);
	public boolean existsByEmail(String email);
	public boolean existsByUsername(String username);
	public List<User> findAll();
}
