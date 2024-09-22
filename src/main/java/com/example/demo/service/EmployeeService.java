package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.KafkaProducerConfig;
import com.example.demo.dto.EmpUtility;
import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.Employee;
import com.example.demo.kafka.producer.KafkaProducerService;
import com.example.demo.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository erepo;
	@Autowired
   KafkaProducerService kps;
	// saving the details

	public UserResponse saveEmpData(UserRequest ureq) {

		Employee emp = EmpUtility.dtoReqEmployee(ureq);
		Employee e = erepo.save(emp);
		UserResponse ures = EmpUtility.employeeToUres(e);
		 //sending a message to kafka brokers ......!!!
		try {
			System.out.println("--------------saving message to kafka brokers-------------------------");
			  ObjectMapper objectMapper = new ObjectMapper();
			
			// Serialize Java object to JSON
	            String userJson = objectMapper.writeValueAsString(ures);
	            System.out.println(ures);
			  System.out.println(userJson);
			kps.sendMessage( "test-topic",ures);
		} catch (Exception e1) {
			System.out.println("error occured while sending message to kafka  for test-topic");
			e1.printStackTrace();
		}
		
		return ures;
	}

	// update the Employee By place and department

	public UserResponse updateByNameandDept(int id, String name, String dept) {
		String data = "";
		UserResponse ures = new UserResponse();
		Optional<Employee> oemp = erepo.findById(id);

		Employee e1 = oemp.orElse(null);
		if (e1 != null) {

			e1.setName(name);
			e1.setDepartment(dept);

			Employee updateEmp = erepo.save(e1);
			data = "employee data has been updated with name " + name + " and depart " + dept;
			ures = EmpUtility.employeeToUres(updateEmp);
			ures.setMessage(data);
           
		} else {

			data = "employee details ot found wit id " + id;

			ures.setMessage(data);
		}

		return ures;

	}

	// delete the employee data

	public UserResponse deleteById(int id) {
		String data = "";
		Optional<Employee> oemp = erepo.findById(id);
		UserResponse ures = new UserResponse();
		if (oemp.isPresent()) {

			erepo.deleteById(id);
			data = "employee deleted with id " + id;
			ures.setMessage(data);
			// return ures;
		} else {
			data = "details not found with id " + id;
			ures.setMessage(data);
		}
		return ures;

	}

	// get all the data
	public List<UserResponse> getAllData() {

		List<UserResponse> lures = new ArrayList<UserResponse>();
		UserResponse ures = new UserResponse();
		try {
			List<Employee> lemp = new ArrayList<Employee>();
			lemp = erepo.findAll();
			lures = EmpUtility.listofUserRes(lemp);
			
//			for(UserResponse ur : lures)
//			System.out.println(ur.getId()+" "+ur.getName()+" "+ur.getDepartment());
			
			
			return lures;
		} catch (Exception e) {
			ures.setMessage("Exception occured while retriving the data");
			lures.add(ures);
		}
		return lures;
	}

	// find data by Employ name and Department

	public List<UserResponse> getByNameAndDepart(String name, String dept) {

		List<UserResponse> lures = new ArrayList<UserResponse>();
		UserResponse ures = new UserResponse();
		try {
			List<Employee> lemp = new ArrayList<Employee>();
			lemp = erepo.findByNameAndDepartment(name, dept);
			lures = EmpUtility.listofUserRes(lemp);
			return lures;
		} catch (Exception e) {
			ures.setMessage("Exception occured while retriving the emp name and department");
			lures.add(ures);
		}
		return lures;
	}

    //find data by Employ Department

	public List<UserResponse> getDepart(String dept) {

		List<UserResponse> lures = new ArrayList<UserResponse>();
		UserResponse ures = new UserResponse();
		try {
			List<Employee> lemp = new ArrayList<Employee>();
			lemp = erepo.findByDepartment(dept);
			
			lures = EmpUtility.listofUserRes(lemp);
			return lures;
		} catch (Exception e) {
			ures.setMessage("Exception occured while retriving the emp name and department");
			lures.add(ures);
		}
		return lures;
	}

}
