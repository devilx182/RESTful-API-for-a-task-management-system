package com.taskmanagmentsystem.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taskmanagmentsystem.config.AppConstantValues;
import com.taskmanagmentsystem.entities.Role;
import com.taskmanagmentsystem.entities.User;
import com.taskmanagmentsystem.payloads.UserDTO;
import com.taskmanagmentsystem.repositories.RoleRepository;
import com.taskmanagmentsystem.repositories.UserRepository;
import com.taskmanagmentsystem.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RoleRepository roleRepository;

	// Register User with admin Role
	@Override
	public UserDTO registerNewADMIN(UserDTO userDTO) {

		User user = this.dtoToUser(userDTO);

		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		Role role = this.roleRepository.findById(AppConstantValues.ADMIN).get();

		user.getRoles().add(role);

		User savedUser = this.userRepository.save(user);

		UserDTO userToDto = this.userToDto(savedUser);

		return userToDto;
	}
	
	// Register User with user Role
	@Override
	public UserDTO registerNewNormalUser(UserDTO userDTO) {

		User user = this.dtoToUser(userDTO);

		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

		Role role = this.roleRepository.findById(AppConstantValues.USER).get();

		user.getRoles().add(role);

		User savedUser = this.userRepository.save(user);

		return this.userToDto(savedUser);
	}

	// Conversion

	private User dtoToUser(UserDTO userDto) {

		User user = this.modelMapper.map(userDto, User.class);
		return user;

	}

	private UserDTO userToDto(User user) {

		UserDTO uDto = this.modelMapper.map(user, UserDTO.class);

		return uDto;

	}

}
