package com.example.nelsdemo;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Employee {
	private int id;
	private String name;
	private double salary;
	private List<String> mobiles;

	public Employee() {
	}

	public Employee(int id, String name, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public Employee(int id, String name, double salary, List<String> mobiles) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.mobiles = mobiles;
	}

	public List<String> getMobiles() {
		return mobiles;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	@Override
	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			System.out.println("Error occured during parsing ::: " + e.getMessage());
			return super.toString();
		}

	}

}
