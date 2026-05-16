package com.example.springWithJdbcTemplate.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private final int errorCode;

    public BusinessException(int errorCode, String message, Throwable cause) {
        super(message, cause); // ส่งต่อ cause ให้ RuntimeException
        this.errorCode = errorCode;
    }
    
    public BusinessException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}