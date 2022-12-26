package com.app.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
	
	private int id;
	
	@NotEmpty
	@Size(min = 4, message = "Name must be atleast 4 characters.")
	private String name;
	
	@Email(message = "Enter a valid email address.")
	@NotEmpty
	private String email;
	
	@NotEmpty
	@Size(min = 5, max = 20, message = "Password must be in range 5-20 inclusive.")
	private String password;
	
	@NotEmpty
	private String about;
	
}
