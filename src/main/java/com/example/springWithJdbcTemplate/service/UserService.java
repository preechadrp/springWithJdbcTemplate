package com.example.springWithJdbcTemplate.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springWithJdbcTemplate.repository.UserRepository;

@Service
public class UserService {

	//แก้ self-invocation ,ไม่ใช่ best practice ,ใช้กรณีเรียก method ใน class ตัวเอง+@Transactional

	//ฟิลด์ที่เป็น final ระบบจะ inject ให้ใน constructor อัตโนมัติ
	private final UserRepository repo;

	public UserService(UserRepository repo) {
		this.repo = repo;
	}

	@Transactional(rollbackFor = Exception.class) //เพื่อให้ db commit
	public void insertUser() {
		repo.insertUser();

		//        กรณีต้องการให้ rollbak แบบ manaul ไม่ต้องสร้าง exception 
		//        if (somethingWrong) {
		//
		//            TransactionAspectSupport
		//                .currentTransactionStatus()
		//                .setRollbackOnly();
		//
		//            return;
		//        }
	}
}
