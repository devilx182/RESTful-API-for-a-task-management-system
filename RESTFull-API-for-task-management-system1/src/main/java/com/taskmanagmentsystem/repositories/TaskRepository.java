package com.taskmanagmentsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmanagmentsystem.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{
	
	
		public List<Task> findByTitleContains(String title);		
		
}
