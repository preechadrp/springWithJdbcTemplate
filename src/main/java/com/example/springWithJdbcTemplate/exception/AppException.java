package com.example.springWithJdbcTemplate.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private final int errorCode;

    public AppException(int errorCode, String message, Throwable cause) {
        super(message, cause); // ส่งต่อ cause ให้ RuntimeException
        this.errorCode = errorCode;
    }
    
    public AppException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}