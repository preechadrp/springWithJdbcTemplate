package com.example.springWithJdbcTemplate.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springWithJdbcTemplate.dao.UserDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	//ฟิลด์ที่เป็น final ระบบจะ inject ให้ใน constructor อัตโนมัติ
	private final UserDao repo;
	
	//แก้ self-invocation ,ไม่ใช่ best practice ,ใช้กรณีเรียก method ใน class ตัวเอง+@Transactional
	//@Autowired
	//private UserService self;

	//ตรงนี้ใช้ @RequiredArgsConstructor สร้างแทน
	//public UserService(UserRepository repo) {
	//	this.repo = repo;
	//}

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
