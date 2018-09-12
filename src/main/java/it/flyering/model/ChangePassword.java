package it.flyering.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

import lombok.Data;

@Data
public class ChangePassword {
	
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	
	@Length(min = 5, message = "*Your old password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your old password")
	private String oldPassword;
	
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your new password")
	@Transient
	private String password;
}
