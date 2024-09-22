package com.example.demo.dto;

public class UserResponse {

    private int id;
	
	private String name;
	
	private String department;
	
	private String place;
	
	private double salary;
	
	private String message;
	
	private int statusCode;

	public UserResponse() {
	}

	public UserResponse(int id, String name, String department, String place, double salary, String message,
			int statusCode) {
		this.id = id;
		this.name = name;
		this.department = department;
		this.place = place;
		this.salary = salary;
		this.message = message;
		this.statusCode = statusCode;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return "UserResponse [id=" + id + ", name=" + name + ", department=" + department + ", place=" + place
				+ ", salary=" + salary + ", message=" + message + ", statusCode=" + statusCode + "]";
	}
	
	
}
