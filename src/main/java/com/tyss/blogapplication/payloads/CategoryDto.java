package com.tyss.blogapplication.payloads;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	@NotBlank
	@Size(min = 4 , message = "category min size should be 4")
	private String categoryTitle;

	@NotBlank
	@Size(min = 10, message = "category discription min size should be 10")
	private String categoryDiscription;
}
