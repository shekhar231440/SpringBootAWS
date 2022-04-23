package com.aws.sqs.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SampleMessage {

	@NotBlank(message="Name can't be left blank")
	private String name;
	@Min(18)
	@Max(65)
	private int age;
	@Pattern( regexp = "[\\p{Alpha}-]*", message="Only alphabets with -(hyphen) are alloed in designation")
	private String designation;
	@Pattern(regexp="[0-9]{10}$")
	private String mobileNumber;
	@Email(message="Provided email id is not valid")
	private String emailId;
}
