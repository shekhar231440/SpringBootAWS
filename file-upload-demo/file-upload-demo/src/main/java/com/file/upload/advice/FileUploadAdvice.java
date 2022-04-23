package com.file.upload.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;

@RestControllerAdvice
public class FileUploadAdvice {
	
	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity handleAmazonS3Exception(AmazonS3Exception amazonS3Exception) {
		
		return new ResponseEntity(amazonS3Exception.getMessage(), HttpStatus.valueOf(Integer.valueOf(amazonS3Exception.getStatusCode())));
	}

}
