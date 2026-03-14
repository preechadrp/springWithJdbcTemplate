package com.example.springWithJdbcTemplate.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springWithJdbcTemplate.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository repo;

	public UserService(UserRepository repo) {
		this.repo = repo;
	}

	@Transactional(rollbackFor = Exception.class) //เพื่อให้ db commit
    public void insertUser() {
        repo.insertUser();
    }
}
