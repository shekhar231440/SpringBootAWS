package com.aws.sns.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

@Configuration
public class AwsSNSConfiguration {

	@Value("${cloud.aws.credentials.accessKey}")
	private String accessKey;
	
	@Value("${cloud.aws.credentials.secretKey}")
	private String secretKey;
	
	
	public AmazonSNSClient amazonSNSClient() {
		
		return (AmazonSNSClient) AmazonSNSClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(
						new BasicAWSCredentials(accessKey, secretKey)))
				.build();		
	}
}
	
