package com.tyss.blogapplication.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.blogapplication.entity.Category;
import com.tyss.blogapplication.exception.CategoryNotFoundException;
import com.tyss.blogapplication.payloads.CategoryDto;
import com.tyss.blogapplication.repository.CategoryRepository;
import com.tyss.blogapplication.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category categoryEntity = this.modelMapper.map(categoryDto, Category.class);
		categoryRepository.save(categoryEntity);
		this.modelMapper.map(categoryEntity, CategoryDto.class);
		return categoryDto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

		Category categoryEntity = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new CategoryNotFoundException("no category found"));

		BeanUtils.copyProperties(categoryDto, categoryEntity);
		Category updatedCategory = categoryRepository.save(categoryEntity);

		this.modelMapper.map(updatedCategory, categoryDto);

		return categoryDto;
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {

		Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

		if (!categoryOptional.isPresent()) {
			throw new CategoryNotFoundException("no category available");
		} else {
			Category category = categoryOptional.get();
			CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);

			return categoryDto;
		}

	}

	@Override
	public List<CategoryDto> getAllCategory() {

		List<Category> categoriesFromDB = categoryRepository.findAll();

		if (categoriesFromDB.isEmpty() || categoriesFromDB == null) {
			throw new CategoryNotFoundException("no category available");
		} else {
			List<CategoryDto> collectedDto = categoriesFromDB.stream().map((cat) ->

			this.modelMapper.map(cat, CategoryDto.class)

			).collect(Collectors.toList());

			return collectedDto;
		}

	}

	@Override
	public void deleteCategory(Integer categoryId) {

		 categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("category not found"));
		categoryRepository.deleteById(categoryId);

	}

}
