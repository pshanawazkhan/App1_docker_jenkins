package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

import jakarta.persistence.Column;
import java.util.List;


@Repository
public interface EmployeeRepository  extends JpaRepository<Employee	, Integer>{

	//List<Employee> findById(int id);
	
	List<Employee>  findByNameAndDepartment(String name,String department);
	
	List<Employee> findByDepartment(String department);
	
	
	
}
