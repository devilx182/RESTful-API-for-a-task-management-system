package com.taskmanagmentsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.taskmanagmentsystem.entities.User;
import com.taskmanagmentsystem.exceptions.ResourceNotFoundException;
import com.taskmanagmentsystem.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Email id : " + username, 0));

		return user;

	}

}
