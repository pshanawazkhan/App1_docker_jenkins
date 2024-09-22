package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;

import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/main")
public class MainController {

	@Autowired
	EmployeeService es;

	@GetMapping("/test")
	public String getMessage() {

		return "Testing--- APP In Docker and aws ";
	}

	// get all data from db
	@GetMapping("/all")
	public ResponseEntity<List<UserResponse>> getAllData() {
		ResponseEntity<List<UserResponse>> response = new ResponseEntity<List<UserResponse>>(HttpStatusCode.valueOf(111));
		List<UserResponse> responseList = new ArrayList<>();
		try {
			responseList = es.getAllData();
			response = ResponseEntity.status(HttpStatus.valueOf(200)).body(responseList);
		} catch (Exception e) {
			// Handle the exception and set an appropriate HTTP status code
			UserResponse ur= new UserResponse();
			ur.setMessage("Error while retriving the All Employee data");
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(ur));
		}

		return response;
	}
	
	
	//saving the data into db
    @PostMapping("/save")
	public ResponseEntity<UserResponse>  save(@RequestBody UserRequest userReq){
	
		ResponseEntity<UserResponse>  response = new ResponseEntity<UserResponse>(HttpStatusCode.valueOf(111));
	
		UserResponse ures = new UserResponse();
		
		try {
			
		  ures=	es.saveEmpData(userReq);
			ures.setMessage("Sucess");
			ures.setStatusCode(200);
		response = ResponseEntity.status(HttpStatusCode.valueOf(201)).body(ures);
		
		return response;
		 
		}catch (Exception e) {

        ures.setMessage("employee details has not been saved error Occured");
			response = ResponseEntity.status(HttpStatusCode.valueOf(500)).body(ures);
		
		return response;
		
		}
	}
	
	//delete the Employee data by id
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<UserResponse> deleteEmployee(@PathVariable  int id){
		
       ResponseEntity<UserResponse>  response = new ResponseEntity<UserResponse>(HttpStatusCode.valueOf(111));
		UserResponse ures = new UserResponse();
		try {
			ures = es.deleteById(id);	
			response = ResponseEntity.status(HttpStatusCode.valueOf(200)).body(ures);	
			return response;
		} catch (Exception e) {
			 ures.setMessage("employee details not found");
				response = ResponseEntity.status(HttpStatusCode.valueOf(500)).body(ures);
			return response;	
		}
	}
	
	//find Employee data by name and department
	@GetMapping("get/{name}/{department}")
	public ResponseEntity<List<UserResponse>>  getEmployeeByNameAndDepartment(@PathVariable String name, @PathVariable String department){
		
		 ResponseEntity<List<UserResponse>>  response = new ResponseEntity<List<UserResponse>>(HttpStatusCode.valueOf(111));
			List<UserResponse> lures =new ArrayList<UserResponse>();
		
			try {
				
			lures=	es.getByNameAndDepart(name, department);
			
			if(lures.size()>0) {
				response = ResponseEntity.status(HttpStatusCode.valueOf(200)).body(lures);
		      
			}else {
				UserResponse ur= new UserResponse();
				ur.setMessage("no data found with name and department details");
				response= ResponseEntity.status(HttpStatusCode.valueOf(404)).body(Collections.singletonList(ur));
				
			}
				return response;
				
			} catch (Exception e) {
				
				UserResponse ur= new UserResponse();
				ur.setMessage("employee details not found with name " + name+" in department " + department);
				
				response= ResponseEntity.status(500).body(Collections.singletonList(ur));
				return response;
			}
		}	
	//find Employee data by  department
		@GetMapping("get/{department}")
		public ResponseEntity<List<UserResponse>>  getEmployeeByNameAndDepartment( @PathVariable String department){
			
			 ResponseEntity<List<UserResponse>>  response = new ResponseEntity<List<UserResponse>>(HttpStatusCode.valueOf(111));
				List<UserResponse> lures =new ArrayList<UserResponse>();
			
				try {
					
				lures=	es.getDepart(department);
                  if(lures.size() >0)		{		
					response = ResponseEntity.status(HttpStatusCode.valueOf(200)).body(lures);
                  }
                  else {
                 UserResponse ur = new UserResponse();
                 ur.setMessage("Employees with department id"+department+" details not found");
                	  response=ResponseEntity.status(404).body(Collections.singletonList(ur));
                  
                  }
                  return response;
					
				} catch (Exception e) {
					
					UserResponse ur= new UserResponse();
					ur.setMessage("employee details not found with department " + department);
					
					response= ResponseEntity.status(500).body(Collections.singletonList(ur));
					return response;
				}
			}	

}
