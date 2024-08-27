package com.taskmanagmentsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmanagmentsystem.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
