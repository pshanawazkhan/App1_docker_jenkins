package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {

	
	@GetMapping("/get")
	public String getMessage() {
				
		return "Deploying APP In Docker and aws ";
	}
	
	@GetMapping("/get1")  
	public String getData() {
		
		
		return "testing the application in  contanirization tool";
	}
	
	
}
