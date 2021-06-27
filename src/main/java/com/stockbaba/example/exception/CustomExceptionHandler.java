package com.stockbaba.example.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "com.stockbaba.example")
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

	private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleAllException(Exception ex,WebRequest request){
		logger.info("landed in handleAllException");
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(ex.getMessage(),details);
		return new ResponseEntity<ErrorResponse>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(StockDetailsException.class)
	public final ResponseEntity<ErrorResponse> handleStockDetailsException(StockDetailsException ex,WebRequest request){
		logger.info("landed in handleStockDetailsException");
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Problem Retrieving Data",details);
		return new ResponseEntity<ErrorResponse>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(StockDetailsNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleStockDetailsNotFoundException(StockDetailsNotFoundException ex,WebRequest request){
		logger.info("landed in handleStockDetailsNotFoundException");
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("No Stock Details",details);
		return new ResponseEntity<ErrorResponse>(error,HttpStatus.NOT_FOUND);
	}
}
