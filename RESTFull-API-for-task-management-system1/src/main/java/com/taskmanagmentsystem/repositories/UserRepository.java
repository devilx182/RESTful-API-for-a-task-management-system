package com.taskmanagmentsystem.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmanagmentsystem.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	// Here we can write custom methods of database
	
	//Method For JWT Authentication
	
	public Optional<User> findByUsername(String username);
}
