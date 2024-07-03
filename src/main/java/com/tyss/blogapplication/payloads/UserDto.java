package com.tyss.blogapplication.payloads;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

	@NotBlank
	@Size(min = 4, max = 14, message = "name should be in specified range😑")
	private String name;

    @Email(message = "Email address is not valid😶")
	private String email;

	@NotBlank
	@Size(min = 4, max = 12, message = "password should be in specified range😒")
	private String password;

	@NotNull
	private String about;
}
