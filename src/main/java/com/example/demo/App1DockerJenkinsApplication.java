package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages = {"com.example.demo","com.example.demo.config", "com.example.demo.service"})
public class App1DockerJenkinsApplication {

	public static void main(String[] args) {
		SpringApplication.run(App1DockerJenkinsApplication.class, args);
	}

}
