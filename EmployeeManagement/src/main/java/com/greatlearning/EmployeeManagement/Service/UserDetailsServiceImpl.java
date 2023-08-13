package com.greatlearning.EmployeeManagement.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greatlearning.EmployeeManagement.Model.User;
import com.greatlearning.EmployeeManagement.Repository.UserRepo;
import com.greatlearning.EmployeeManagement.SecurityConfig.MyUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.getUserByUserName(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("Couldn't find user  ");
		}
		
		return new MyUserDetails(user);
		
		
	}

}
