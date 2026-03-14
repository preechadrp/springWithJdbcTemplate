package com.example.springWithJdbcTemplate.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(org.springframework.dao.DataAccessException.class)
	public ResponseEntity<?> dbError(Exception ex) {

		return ResponseEntity
				.status(500)
				.body(Map.of("error", ex.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handle(Exception ex) {

		ex.printStackTrace();

		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(Map.of("error", ex.getMessage()));
	}

}