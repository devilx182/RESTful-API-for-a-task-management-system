package com.taskmanagmentsystem.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(

		info = @Info(

				title = "Task Managment System Project", description = "This is a RESTFul API for Task Management System",contact = @Contact(name = "Swapnil Tole", email = "swapnil.info2020@gmail.com"),

				license = @License(name = "Open License !!"),

				version = "1.0V"),

		servers = {
				@Server(description = "This is a Devlopment Server for my own machine !!", url = "http://localhost:8080") }, security = @SecurityRequirement(

						name = "auth")

)
@SecurityScheme(name = "auth", in = SecuritySchemeIn.HEADER, type = SecuritySchemeType.HTTP, bearerFormat = "JWT", description = "Apply Security/Authentication on All API", scheme = "bearer"

)

@Configuration
public class SwaggerOpenAPIConfig {
}
