package com.stockbaba.example.exception;

public class StockDetailsException extends RuntimeException{

	public StockDetailsException() {
		super();
	}

	public StockDetailsException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
