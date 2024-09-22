package com.example.demo.dto;

public class UserRequest {

	
    private int id;
	
	private String name;
	
	private String department;
	
	private String place;
	
	private double salary;

	
	public UserRequest() {
	
	}

	public UserRequest(int id, String name, String department, String place, double salary) {
		this.id = id;
		this.name = name;
		this.department = department;
		this.place = place;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	
}
