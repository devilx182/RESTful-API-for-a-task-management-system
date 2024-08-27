package com.taskmanagmentsystem.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "UserTask")
@NoArgsConstructor
@Setter
@Getter
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "taskId")
	private int id;

	@Column(name = "title", nullable = false, length = 100)
	private String title;

	@Column(name = "description", nullable = false, length = 200)
	private String description;

	@Column(name = "status")
	private String status;

	@Column(name = "priority")
	private String priority;

	@Column(name = "dueDate")
	private String dueDate;

	@Column(name = "created_at")
	private Date created_At;
	
	@Column(name = "updated_at")
	private Date updated_at;

}
