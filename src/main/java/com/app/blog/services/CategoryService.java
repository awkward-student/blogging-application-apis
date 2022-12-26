package com.app.blog.services;

import java.util.List;

import com.app.blog.payloads.CategoryDTO;

public interface CategoryService {

	CategoryDTO createCategory(CategoryDTO categoryDto);
	
	CategoryDTO updateCategory(CategoryDTO categoryDto, Integer categoryId);
	
	void deleteCategory(Integer categoryId);
	
	CategoryDTO getCategory(Integer categoryId);
	
	List<CategoryDTO> getCategories();
}
