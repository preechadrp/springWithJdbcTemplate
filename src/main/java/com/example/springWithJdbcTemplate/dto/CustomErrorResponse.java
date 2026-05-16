package com.example.springWithJdbcTemplate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponse {
	@JsonProperty("error_code")
	private int errorCode;

	@JsonProperty("error_message")
	private String errorMessage;
}
