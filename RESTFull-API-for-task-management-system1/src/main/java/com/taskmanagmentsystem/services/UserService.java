package com.taskmanagmentsystem.services;

import org.springframework.stereotype.Service;

import com.taskmanagmentsystem.payloads.UserDTO;

@Service
public interface UserService {

	public UserDTO registerNewADMIN(UserDTO userDTO);

	public UserDTO registerNewNormalUser(UserDTO userDTO);

}
