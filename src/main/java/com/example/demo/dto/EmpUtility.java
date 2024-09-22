package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;


import com.example.demo.entity.Employee;

public class EmpUtility {

	public static Employee dtoResToEmployee(UserResponse ures) {
		
		
		Employee emp = new Employee();
		BeanUtils.copyProperties(ures, emp);
		
		return emp;
	}
	
	
	public static Employee dtoReqEmployee(UserRequest ureq) {
		
		Employee emp = new Employee();
		BeanUtils.copyProperties(ureq, emp);
		System.out.println(emp.getDepartment()+"  "+emp.getName());
		return emp;	
	}
	
	
	public static UserRequest employeeToUreq(Employee emp) {
		
		UserRequest ur = new UserRequest();
		BeanUtils.copyProperties(emp, ur);
		return ur;
		
	}
	
	public static UserResponse employeeToUres(Employee emp) {
		
		UserResponse ureq = new UserResponse();
		BeanUtils.copyProperties(emp, ureq);
		return ureq;
		
	}	
	
	
	public static List<UserResponse>  listofUserRes(List<Employee> lemp){
		
		List<UserResponse> res = new ArrayList<UserResponse>();
		
		for(Employee emp : lemp) {
			UserResponse uresp = new UserResponse();
			BeanUtils.copyProperties(emp, uresp);
		
			res.add(uresp);
			
		}
		
		return res;
	}
	
}
