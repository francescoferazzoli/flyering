package it.flyering.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	@Transient
	private String password;
	
	@NotEmpty(message = "*Please provide your name")
	private String name;
	
	@NotEmpty(message = "*Please provide your last name")
	private String lastName;
}
