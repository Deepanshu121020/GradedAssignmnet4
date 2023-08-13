	package com.greatlearning.EmployeeManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.EmployeeManagement.Model.Employee;
import com.greatlearning.EmployeeManagement.Service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/allEmployees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@PostMapping("/addEmployee")
	public Employee addNewEmployee(@RequestParam("firstName") String firstname,
			@RequestParam("lastName") String lastname, @RequestParam("email") String email) {
		Employee employee = new Employee();
		employee.setFirstName(firstname);
		employee.setLastName(lastname);
		employee.setEmail(email);

		return employeeService.addNewEmployee(employee);
	}

	@GetMapping("/{id}")
	@ResponseBody
	public Employee getEmployeeById(@PathVariable("id") long id) {
		return employeeService.getEmployeeById(id);
	}

	@DeleteMapping
	public String deleteEmployeeById(@RequestParam("id") long id) {
		employeeService.deleteEmployee(id);
		return "employee is deleted";
	}

	@PutMapping("/updateEmployee")
	public Employee updateEmployee(@RequestParam("id") long id, @RequestBody Employee employee) {
		return employeeService.updateEmployee(id, employee);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable("id") long id) {
		employeeService.deleteEmployeeById(id);
		return "employee " + id + "is deleted";
	}

	@GetMapping("/getEmployeeByName")
	public List<Employee> getEmployeeByName(@RequestParam("firstName") String firstName) {

		return employeeService.searchByFirstAndOrLastName(firstName);
	}

}
