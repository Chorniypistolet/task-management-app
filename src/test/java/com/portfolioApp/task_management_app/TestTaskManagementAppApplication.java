package com.portfolioApp.task_management_app;

import org.springframework.boot.SpringApplication;

public class TestTaskManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.from(TaskManagementAppApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
