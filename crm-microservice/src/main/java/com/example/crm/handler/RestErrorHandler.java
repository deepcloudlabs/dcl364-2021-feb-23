package com.example.crm.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.crm.dto.RestErrorMessage;

@RestControllerAdvice
public class RestErrorHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(code =  HttpStatus.BAD_REQUEST)
	public RestErrorMessage handleIllegalArgumentException(IllegalArgumentException e) {
		return new RestErrorMessage(e.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code =  HttpStatus.BAD_GATEWAY)
	public RestErrorMessage handleException(Exception e) {
		return new RestErrorMessage(e.getMessage());
	}
}
