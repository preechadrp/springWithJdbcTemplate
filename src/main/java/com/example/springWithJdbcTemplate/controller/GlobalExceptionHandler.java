package com.example.springWithJdbcTemplate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.springWithJdbcTemplate.dto.CustomErrorResponse;
import com.example.springWithJdbcTemplate.exception.BusinessException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<CustomErrorResponse> handleBusinessException(BusinessException ex) {

		log.error("Business Error: Code={}, Message={}", ex.getErrorCode(), ex.getMessage(), ex);

		CustomErrorResponse error = new CustomErrorResponse(ex.getErrorCode(), ex.getMessage());

		// ตรวจสอบว่า errorCode เป็น HTTP Status (400-599) หรือไม่
		// ถ้าไม่ใช่ (เช่น เป็นเลข 1, 2, 3) ให้ส่ง HTTP 400 Bad Request กลับไปแทน
		HttpStatus status = HttpStatus.resolve(ex.getErrorCode());
		if (status == null || !status.isError()) {
			status = HttpStatus.BAD_REQUEST;
		}

		return ResponseEntity.status(status).body(error);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<CustomErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		log.warn("Parameter Mismatch: {}", ex.getMessage());
		CustomErrorResponse error = new CustomErrorResponse(400, "Invalid parameter type: " + ex.getName());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomErrorResponse> handleGeneralException(Exception ex) {
		// บันทึก StackTrace เต็มๆ ลง Log สำหรับไล่ Bug แต่ไม่ต้องส่งออกไปหา User
		log.error("Unexpected System Error: ", ex);
		CustomErrorResponse error = new CustomErrorResponse(500, "Internal Server Error");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}

}