package com.greatlearning.EmployeeManagement.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.EmployeeManagement.Service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(myAuth());

	}

	private AuthenticationProvider myAuth() {

		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(myUser());
		authProvider.setPasswordEncoder(myPass());
		return authProvider;

	}
	
	@Bean
	protected UserDetailsService myUser() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	protected PasswordEncoder myPass() {
		return new BCryptPasswordEncoder();
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http. 
			authorizeRequests()
			.antMatchers("/employee/allEmployees","employee/addEmployee","employee/getEmployeeByName",
						 "employee/{id}").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/employee/updateEmployee","employee/delete/{id}").hasAuthority("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
			
	}
}