package com.taskmanagmentsystem.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taskmanagmentsystem.payloads.TaskDTO;
import com.taskmanagmentsystem.payloads.TaskPagination;

@Service
public interface TaskService {

	// Create Task
	public TaskDTO createTask(TaskDTO taskDTO);

	// Get Tasks
	public TaskPagination getAllTask(Integer page, Integer pageSize, String sortBy, String sortDirection);

	// Update Task
	public TaskDTO updateTask(TaskDTO taskDTO, int taskID);

	// Delete Task
	public void deleteTask(int taskID);

	// Search task by title
	public List<TaskDTO> searchTask(String title);

}
