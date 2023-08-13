package com.greatlearning.EmployeeManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greatlearning.EmployeeManagement.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	@Query("Select u FROM User u WHERE u.username = ?1")
	public User getUserByUserName(String username);

}
