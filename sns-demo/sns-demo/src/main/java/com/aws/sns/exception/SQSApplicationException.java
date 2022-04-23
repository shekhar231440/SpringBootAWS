package com.aws.sns.exception;

public class SQSApplicationException extends Exception {

	public SQSApplicationException(String errorMessage) {
		
		super(errorMessage);
	}
}
