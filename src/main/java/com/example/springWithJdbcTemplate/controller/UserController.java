package com.example.springWithJdbcTemplate.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springWithJdbcTemplate.dao.UserDao;
import com.example.springWithJdbcTemplate.dao.UserDao2;
import com.example.springWithJdbcTemplate.dto.User;
import com.example.springWithJdbcTemplate.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j  //สร้างตัวแปร log อัตโนมัติ
@RestController
public class UserController {

	private final UserDao usersRepo;
	private final UserDao2 usersRepo2;
	private final UserService userService;

	public UserController(UserDao usersRepo,
			UserDao2 usersRepo2,
			UserService userService) {

		this.usersRepo = usersRepo;
		this.usersRepo2 = usersRepo2;
		this.userService = userService;
	}

	@GetMapping("/api/user")
	public List<User> getAllUser() {
		log.info("/api/user");
		return this.usersRepo.findAll();
	}

	@GetMapping("/api/user2")
	public List<User> getAllUser2() {
		log.info("/api/user2");
		return this.usersRepo2.findAll();
	}

	@GetMapping("/api/userInsert")
	public List<User> insertUser() {
		this.userService.insertUser();
		return this.usersRepo.findAll();
	}

}
