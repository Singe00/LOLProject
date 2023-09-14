package com.example.lwp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LwpApplication {

	public static void main(String[] args) {
		SpringApplication.run(LwpApplication.class, args);
	}

}
