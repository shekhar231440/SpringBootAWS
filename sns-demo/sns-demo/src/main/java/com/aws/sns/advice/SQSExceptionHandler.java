package com.aws.sns.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aws.sns.exception.SQSApplicationException;

@RestControllerAdvice
public class SQSExceptionHandler {
	
	
	@ExceptionHandler(SQSApplicationException.class)
	public Map<String, String> handleSQSExceptions(SQSApplicationException exception){
		
		Map<String, String> errorMap = new HashMap<>();
		
		errorMap.put("Error Message", exception.getLocalizedMessage());
		
		return errorMap;
		
	}

}
