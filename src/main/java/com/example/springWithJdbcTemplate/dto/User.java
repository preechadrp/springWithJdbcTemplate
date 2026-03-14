package com.example.springWithJdbcTemplate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Builder(toBuilder = true) //เพื่อให้สามารถสร้าง object ใหม่ได้จาก object เดิมด้วยคำสั่ง objOld.toBuilder().build(); เป็นการ clone มาเป็นตัวใหม่
@Accessors(chain = true) //ทำให้ใช้คำสั่ง User mm = new User().setUserid(1).setUsername("kk"); ได้
@AllArgsConstructor //สร้าง method Constructor แบบมี Argument ทุก member
@NoArgsConstructor //สร้าง method Constructor แบบไม่มี Argument
@Getter
@Setter
public class User {

	private int userid;
	private String username;
	private java.time.LocalDate birthDay;
	private String createdBy;
	private java.time.LocalDateTime createdDate;

}