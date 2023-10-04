package com.example.lwp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.example.lwp")
public class LwpApplication {

	public static void main(String[] args) {
		SpringApplication.run(LwpApplication.class, args);
	}

}
