package com.example.demo.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		String users_query = "INSERT INTO USERS(USERNAME, PASSWORD, ENABLED) VALUES(?,?,?)";
		jdbcTemplate.update(users_query, "Allen", passwordEncoder.encode("allen@123"), true);
		jdbcTemplate.update(users_query, "Blake", passwordEncoder.encode("blake@456"), false);
		jdbcTemplate.update(users_query, "Mary", passwordEncoder.encode("mary@789"), true);
		
		String authorities_query = "INSERT INTO AUTHORITIES(USERNAME, AUTHORITY) VALUES(?,?)";
		jdbcTemplate.update(authorities_query, "Allen", "ROLE_ADMIN");
		jdbcTemplate.update(authorities_query, "Blake", "ROLE_USER");
		jdbcTemplate.update(authorities_query, "Mary", "ROLE_HR");
		
		System.out.println("Records are inserted.");
	}

}
