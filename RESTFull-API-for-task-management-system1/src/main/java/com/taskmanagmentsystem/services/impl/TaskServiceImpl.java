package com.taskmanagmentsystem.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.taskmanagmentsystem.entities.Task;
import com.taskmanagmentsystem.exceptions.ResourceNotFoundException;
import com.taskmanagmentsystem.payloads.TaskDTO;
import com.taskmanagmentsystem.payloads.TaskPagination;
import com.taskmanagmentsystem.repositories.TaskRepository;
import com.taskmanagmentsystem.services.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TaskRepository taskRepository;

	// Create Task

	@Override
	public TaskDTO createTask(TaskDTO taskDTO) {
		Task task = this.taskDTOToTask(taskDTO);
		task.setCreated_At(new java.util.Date());
		Task save = this.taskRepository.save(task);
		TaskDTO taskDTO1 = this.taskTotaskDTO(save);
		return taskDTO1;
	}

	// Get All Task with Pagination

	@Override
	public TaskPagination getAllTask(Integer page, Integer pageSize, String sortBy, String sortDirection) {

		Sort sort = null;

		if (sortDirection.equalsIgnoreCase("ascending")) {
			sort = Sort.by(sortBy).ascending();
		} else {
			sort = Sort.by(sortBy).descending();
		}

		PageRequest pageRequest = PageRequest.of(page, pageSize, sort);

		Page<Task> pagePost = this.taskRepository.findAll(pageRequest);

		List<Task> content = pagePost.getContent();

		List<TaskDTO> taskDto = content.stream().map(tas -> this.taskTotaskDTO(tas)).collect(Collectors.toList());

		TaskPagination taskPagination = new TaskPagination();

		taskPagination.setContent(taskDto);
		taskPagination.setPageNumber(pagePost.getNumber());
		taskPagination.setPageSize(pagePost.getSize());
		taskPagination.setTotalElements(pagePost.getTotalElements());
		taskPagination.setTotalPages(pagePost.getTotalPages());
		taskPagination.setLastPage(pagePost.isLast());

		return taskPagination;
	}

	// Update Task

	@Override
	public TaskDTO updateTask(TaskDTO taskDTO, int taskID) {
		Task task = this.taskRepository.findById(taskID)
				.orElseThrow(() -> new ResourceNotFoundException("Task", "task id", taskID));
		task.setTitle(taskDTO.getTitle());
		task.setDescription(taskDTO.getDescription());
		task.setStatus(taskDTO.getStatus());
		task.setPriority(taskDTO.getPriority());
		task.setUpdated_at(new java.util.Date());

		Task updateTask = this.taskRepository.save(task);
		TaskDTO taskDTO2 = this.taskTotaskDTO(updateTask);
		return taskDTO2;
	}

	// Delete

	@Override
	public void deleteTask(int taskID) {
		Task task = this.taskRepository.findById(taskID)
				.orElseThrow(() -> new ResourceNotFoundException("Task", "task id", taskID));

		this.taskRepository.delete(task);

	}

	// Search Task
	@Override
	public List<TaskDTO> searchTask(String title) {
		List<Task> task = this.taskRepository.findByTitleContains(title);
		List<TaskDTO> TaskDTO = task.stream().map((t) -> this.taskTotaskDTO(t)).collect(Collectors.toList());

		return TaskDTO;

	}

	// Conversion :
	private Task taskDTOToTask(TaskDTO taskDTO) {
		Task task = this.modelMapper.map(taskDTO, Task.class);
		return task;

	}

	private TaskDTO taskTotaskDTO(Task task) {

		TaskDTO task2 = this.modelMapper.map(task, TaskDTO.class);
		return task2;
	}

}
