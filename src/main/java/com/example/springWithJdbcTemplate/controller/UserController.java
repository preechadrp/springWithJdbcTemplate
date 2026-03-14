package com.example.springWithJdbcTemplate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springWithJdbcTemplate.dto.User;
import com.example.springWithJdbcTemplate.repository.UserRepository;
import com.example.springWithJdbcTemplate.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserRepository usersRepo;

	@Autowired
	private UserService userService;

	@GetMapping("/api/user")
	public List<User> getAllUser() {
		return this.usersRepo.findAll();
	}

	@GetMapping("/api/userInsert")
	public List<User> insertUser() {
		this.userService.insertUser();
		return this.usersRepo.findAll();
	}

}
