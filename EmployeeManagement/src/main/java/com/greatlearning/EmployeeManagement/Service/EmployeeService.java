package com.greatlearning.EmployeeManagement.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.EmployeeManagement.Model.Employee;
import com.greatlearning.EmployeeManagement.Repository.EmployeeRepo;

@Service

public class EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;

	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

	public Employee addNewEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}

	public void deleteEmployee(long id) {
		employeeRepo.deleteById(id);

	}

	public Employee updateEmployee(long id, Employee employee) {
		Employee employeeDb = employeeRepo.findById(id).get();
		employeeDb.setFirstName(employee.getFirstName());
		employeeDb.setLastName(employee.getLastName());
		employeeDb.setEmail(employee.getEmail());
		return employeeRepo.save(employeeDb);
	}

	public Employee getEmployeeById(long id) {
		return employeeRepo.findById(id).get();
	}

	public void deleteEmployeeById(long id) {
		employeeRepo.deleteById(id);

	}

	
	  public List<Employee> searchByFirstAndOrLastName(String firstName) 
	  { List<Employee> employees =
	  employeeRepo.searchByFirstAndOrLastName(firstName);
	  
	  if (employees.size() > 0) { return employees; } else { return new
	  ArrayList<Employee>(); } }
	 

}
