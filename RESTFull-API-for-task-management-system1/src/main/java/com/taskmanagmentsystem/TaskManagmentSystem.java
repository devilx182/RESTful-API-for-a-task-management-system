package com.taskmanagmentsystem;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.taskmanagmentsystem.config.AppConstantValues;
import com.taskmanagmentsystem.entities.Role;
import com.taskmanagmentsystem.repositories.RoleRepository;


@SpringBootApplication
public class TaskManagmentSystem implements CommandLineRunner{

	@Autowired
	private RoleRepository roleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TaskManagmentSystem.class, args);
		
		System.out.println("Server Started Successfully !!");
	}
    
	@Bean
	public ModelMapper modelmapper() {
		
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		
		try {
		Role role1 = new Role();
		role1.setId(AppConstantValues.ADMIN);
		role1.setName("ADMIN");
		
		Role role2 =new Role();
		role2.setId(AppConstantValues.USER);
		role2.setName("USER");
		
		List<Role> roles= List.of(role1,role2);
		
		List<Role> saveAll = this.roleRepository.saveAll(roles);
		
		saveAll.forEach(s->
				System.out.println(s.getName())
				);
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	
	
	
	
	}
}
