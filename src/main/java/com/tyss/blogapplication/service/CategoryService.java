package com.tyss.blogapplication.service;

import java.util.List;

import com.tyss.blogapplication.payloads.CategoryDto;

public interface CategoryService {

	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//get
	CategoryDto getCategory(Integer categoryId);
	
	//get all
	List<CategoryDto> getAllCategory();
	
	//delete
	void deleteCategory(Integer categoryId);
	
	

}
