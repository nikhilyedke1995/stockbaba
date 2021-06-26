package com.stockbaba.example.exception;

import java.util.List;

public class ErrorResponse {

	private String message;
	
	private List<String> details;

	public ErrorResponse(String message, List<String> details) {
		this.message = message;
		this.details = details;
	}
	
	
	
}
