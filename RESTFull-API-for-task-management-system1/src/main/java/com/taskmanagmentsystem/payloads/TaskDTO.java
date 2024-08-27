package com.taskmanagmentsystem.payloads;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaskDTO {

	private int id;

	@NotEmpty
	private String title;

	@NotEmpty
	private String description;

	@NotEmpty
	private String status;

	@NotEmpty
	private String priority;

	private String dueDate;

	private Date created_At;

	private Date updated_at;

}
