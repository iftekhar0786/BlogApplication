package com.tyss.blogapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.blogapplication.payloads.CategoryDto;
import com.tyss.blogapplication.response.Response;
import com.tyss.blogapplication.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CategoryController {

	
	private final CategoryService categoryService;

	@PostMapping("/saveCategory")
	public ResponseEntity<Response> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto category = categoryService.createCategory(categoryDto);
		return new ResponseEntity<Response>(new Response(false, "category saved successfully", category),
				HttpStatus.CREATED);
	}

	@PutMapping("/updateCategory")
	public ResponseEntity<Response> updateCategory(@Valid  @RequestBody CategoryDto categoryDto, Integer categoryId) {
		CategoryDto updateCategory = categoryService.updateCategory(categoryDto, categoryId);

		return new ResponseEntity<Response>(new Response(false, "category updated successfully", updateCategory),
				HttpStatus.OK);
	}

	@GetMapping("/getCategory/{categoryId}")
	public ResponseEntity<Response> getCategory(@PathVariable Integer categoryId) {
		CategoryDto category = categoryService.getCategory(categoryId);
		return new ResponseEntity<Response>(new Response(false, "fetched category successfully", category),
				HttpStatus.OK);

	}

	@GetMapping("/getAllCategory")
	public ResponseEntity<Response> getCategories() {
		List<CategoryDto> allCategory = categoryService.getAllCategory();
		return new ResponseEntity<Response>(new Response(false, "fetched all category successfully", allCategory),
				HttpStatus.OK);
	}

	@DeleteMapping("/deleteCategory/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable Integer categoryId) {

		categoryService.deleteCategory(categoryId);

		return new ResponseEntity<>("category deleted successfully", HttpStatus.OK);
	}

}
