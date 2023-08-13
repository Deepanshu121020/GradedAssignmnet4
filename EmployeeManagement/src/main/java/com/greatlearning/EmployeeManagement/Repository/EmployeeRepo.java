package com.greatlearning.EmployeeManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greatlearning.EmployeeManagement.Model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	
	  @Query("select u from Employee u where u.firstName = ?1 or u.lastName = ?2"
	  ) List<Employee> searchByFirstAndOrLastName(String firstName);
	 
}
