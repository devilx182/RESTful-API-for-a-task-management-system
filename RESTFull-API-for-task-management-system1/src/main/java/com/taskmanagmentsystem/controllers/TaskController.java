package com.taskmanagmentsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagmentsystem.config.AppConstantValues;
import com.taskmanagmentsystem.payloads.ApiResponse;
import com.taskmanagmentsystem.payloads.TaskDTO;
import com.taskmanagmentsystem.payloads.TaskPagination;
import com.taskmanagmentsystem.services.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class TaskController {

	@Autowired
	private TaskService taskService;

	// Create Task
	@Operation(tags = "Task create API", description = "This API used for create task !!",

			responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Task created successfully !!", responseCode = "201") })
	@PreAuthorize("hasAuthority('USER')")
	@PostMapping("/tasks")
	public ResponseEntity<TaskDTO> createHandler(@Valid @RequestBody TaskDTO taskDTO) {

		TaskDTO createTask = this.taskService.createTask(taskDTO);
		return new ResponseEntity<>(createTask, HttpStatus.CREATED);
	}

	// Get All Task with Pagination with filter

	@Operation(tags = "See All Task API", description = "This API used to see all task with pagination and filtering the task !!",

			responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Task created successfully !!", responseCode = "200") })
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/tasks")
	public ResponseEntity<TaskPagination> getAllHandler(
			@RequestParam(value = "page", defaultValue = AppConstantValues.PAGE_NUMBER, required = false) Integer page,
			@RequestParam(value = "pageSize", defaultValue = AppConstantValues.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstantValues.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = AppConstantValues.SORT_DIRECTION, required = false) String sortDirection) {

		TaskPagination allTask = this.taskService.getAllTask(page, pageSize, sortBy, sortDirection);

		return new ResponseEntity<TaskPagination>(allTask, HttpStatus.OK);
	}

	// Update Task
	@Operation(tags = "Task update API", description = "This API used for update the task !!",

			responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Task update successfully !!", responseCode = "200") })
	@PreAuthorize("hasAuthority('USER')")
	@PutMapping("/tasks/{taskId}")
	public ResponseEntity<TaskDTO> updateHandler(@Valid @RequestBody TaskDTO taskDTO, @PathVariable Integer taskId) {
		TaskDTO updateTask = this.taskService.updateTask(taskDTO, taskId);

		return new ResponseEntity<>(updateTask, HttpStatus.OK);
	}

	// Delete Task
	@Operation(tags = "Task delete API", description = "This API used for delete task !!",

			responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Task delete successfully !!", responseCode = "200") })
	@PreAuthorize("hasAuthority('USER')")
	@DeleteMapping("/tasks/{taskId}")
	public ResponseEntity<ApiResponse> deleteHandler(@PathVariable Integer taskId) {

		this.taskService.deleteTask(taskId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Task Deleted Successfully !!", true), HttpStatus.OK);

	}

	// Task Search
	@Operation(tags = "Task search API", description = "This API used for search task !!",

			responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Task found successfully !!", responseCode = "200") })
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/task/search/{title}")
	public ResponseEntity<List<TaskDTO>> getPostByTitleKeyword(@PathVariable String title) {

		List<TaskDTO> taskDTO = this.taskService.searchTask(title);

		return new ResponseEntity<List<TaskDTO>>(taskDTO, HttpStatus.OK);

	}
}
