package com.aws.sns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.aws.sns.exception.SQSApplicationException;

@RestController
public class AwsSNSController {
	
	private static final String SNS_ARN="arn:aws:sns:us-east-1:763258292230:MyFirstTopic";
	
	@Autowired
	public AmazonSNSClient amazonSNSClient;

	@GetMapping("/subscribe/{email}")
	public String subscribeTopic(@PathVariable("email") String email) {
		
		SubscribeRequest subscribeRequest = new SubscribeRequest(SNS_ARN, "email", email);		
		amazonSNSClient.subscribe(subscribeRequest);
		return "Check your email";		
	}
	
	@GetMapping("/publish/{msg}")
	public String publishMessage(@PathVariable("msg") String message) throws SQSApplicationException{
		
		try {
			amazonSNSClient.publish(SNS_ARN, message, "Sample message from spring boot");
		} catch (Exception e) {
			throw new SQSApplicationException(e.getMessage());
		}
		return "provided message has been published successfully...";
		
	}
	
}
