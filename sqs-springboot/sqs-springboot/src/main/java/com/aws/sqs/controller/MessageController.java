package com.aws.sqs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aws.sqs.exception.MessageNotFoundException;
import com.aws.sqs.model.SampleMessage;


@RestController
public class MessageController {
	
	@Autowired
	private QueueMessagingTemplate messagingTemplate;
	
	@Value("${cloud.aws.end-point.uri}")
	private String destination;
	
	@PostMapping("/sendMessage")
	public String sendMessage(@RequestBody @Valid SampleMessage message) {
		
		Message payload = MessageBuilder.withPayload(message)
				.setHeader("senderName", "Chandar Shekhar")
				.build();
		
		messagingTemplate.send(destination, payload);
		
		return "Message is successfully sent to AWS Queue...";
	}
	
	@GetMapping("/getMessage")
	public Message getMessage() throws MessageNotFoundException {
		
		Message messageFromSQS =  messagingTemplate.receive(destination);
		
		if(messageFromSQS!= null)
			return messageFromSQS;
		else
			throw new MessageNotFoundException("No Message present to retrieve");
	}
	
	/*
	@SqsListener(value = "MyFirstQueue")
	public void loadMessageFromQueue(String message) {
		
		 System.out.println(message);
	}
*/
}
