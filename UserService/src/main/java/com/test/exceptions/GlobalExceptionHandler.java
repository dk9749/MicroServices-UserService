package com.test.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.test.exceptions.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(
			ResourceNotFoundException resourceNotFoundException) {
		ApiResponse apiResponse = new ApiResponse("Resouce not found !!!!", false, HttpStatus.NOT_FOUND);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

	}
}
