package com.stockbaba.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StockDetailsNotFoundException extends RuntimeException{

	public StockDetailsNotFoundException() {
		
	}

	public StockDetailsNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public StockDetailsNotFoundException(String message) {
		super(message);
	}
	
	
	
}
