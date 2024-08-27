package com.taskmanagmentsystem.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UserDTO {

	private int id;

	@NotEmpty
	@Size(min = 4, message = "Username must be in minimum of 4 Characters !!")
	private String username;

	@NotEmpty
	@Size(min = 8, message = "Password must be min 8 chars !!")
	private String password;

}
