package com.eta.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.sun.glass.ui.Application;

@SpringBootApplication
public class ServicesApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(ServicesApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(ServicesApplication.class);
	}

}
