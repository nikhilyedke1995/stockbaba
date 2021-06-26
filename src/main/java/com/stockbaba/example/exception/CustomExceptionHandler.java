package com.stockbaba.example.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex){
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(ex.getMessage(),details);
		return new ResponseEntity(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(StockDetailsException.class)
	public final ResponseEntity<Object> handleStockDetailsException(StockDetailsException ex){
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Problem Retrieving Data",details);
		return new ResponseEntity(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(StockDetailsNotFoundException.class)
	public final ResponseEntity<Object> handleStockDetailsNotFoundException(StockDetailsNotFoundException ex){
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("No Stock Details",details);
		return new ResponseEntity(error,HttpStatus.NOT_FOUND);
	}
}
