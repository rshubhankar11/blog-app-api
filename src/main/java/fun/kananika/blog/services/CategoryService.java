package fun.kananika.blog.services;

import java.util.List;

import fun.kananika.blog.payloads.CategoryDto;

public interface CategoryService {
	
//	create
	CategoryDto createCategory(CategoryDto categoryDto);
	
//	update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
//	delete
	void deleteCategory(CategoryDto categoryDto);

//	get by id
	CategoryDto getCategoryDto(Integer categoryId);

//	get all
	List<CategoryDto> getAllCategoryDto();

}
