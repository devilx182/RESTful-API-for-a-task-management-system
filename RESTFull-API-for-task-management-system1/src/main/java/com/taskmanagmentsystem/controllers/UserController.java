package com.taskmanagmentsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagmentsystem.payloads.UserDTO;
import com.taskmanagmentsystem.security.CustomUserDetailsService;
import com.taskmanagmentsystem.security.JwtAuthRequest;
import com.taskmanagmentsystem.security.JwtAuthResponce;
import com.taskmanagmentsystem.security.JwtTokenHelper;
import com.taskmanagmentsystem.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")

public class UserController {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenHelper tokenHelper;

	@Autowired
	private UserService userService;

	// Register new User

	@Operation(tags = "User Registration API", description = "This API used for new user register !!",

			responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse(description = "User created successfully !!", responseCode = "201") })
	@PostMapping("/register")

	public ResponseEntity<UserDTO> registerNewUSER(@Valid @RequestBody UserDTO userDTO) {

		UserDTO createdUserDTO = this.userService.registerNewNormalUser(userDTO);
		return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);

	}

	// Register new admin by admin

	@Operation(tags = "Admin Registration API", description = "This API used for new admin register by admin !!",

			responses = {

					@io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Access Denied !! Unauthorized only admin access !!", responseCode = "401"),

					@io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Admin created successfully !!", responseCode = "201") })
	@PostMapping("/adminregister")
	// @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<UserDTO> registerNewADMIN(@Valid @RequestBody UserDTO userDTO) {

		UserDTO registerNewUser = this.userService.registerNewADMIN(userDTO);

		return new ResponseEntity<UserDTO>(registerNewUser, HttpStatus.CREATED);

	}

	// Login API

	@Operation(tags = "Login API", description = "This API used for login !!",

			responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Login successfully !!", responseCode = "200") })
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponce> login(@RequestBody JwtAuthRequest request) {

		this.authenticate(request.getUsername(), request.getPassword());

		UserDetails loadUserByUsername = this.customUserDetailsService.loadUserByUsername(request.getUsername());

		String generatedToken = this.tokenHelper.generateToken(loadUserByUsername);

		JwtAuthResponce authResponce = new JwtAuthResponce();
		authResponce.setJwtToken(generatedToken);
		authResponce.setUsername(tokenHelper.getUsernameFromToken(generatedToken));

		return new ResponseEntity<JwtAuthResponce>(authResponce, HttpStatus.OK);

	}

	private void authenticate(String username, String password) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		this.authenticationManager.authenticate(authenticationToken);

	}

}
