package fun.kananika.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.kananika.blog.entity.Category;
import fun.kananika.blog.entity.User;
import fun.kananika.blog.exceptions.ResourceNotFoundException;
import fun.kananika.blog.payloads.CategoryDto;
import fun.kananika.blog.payloads.UserDTO;
import fun.kananika.blog.repositories.CategoryRepo;
import fun.kananika.blog.services.CategoryService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.dtoToEntity(categoryDto);
		Category addedCat = this.categoryRepo.save(category);
		CategoryDto dto = this.entityToDto(addedCat);
		return dto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());

		Category updatedCat = this.categoryRepo.save(cat);
		CategoryDto dto = this.entityToDto(updatedCat);

		return dto;
	}

	@Override
	public void deleteCategory(Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId)

				);
		this.categoryRepo.delete(cat);

	}

	@Override
	public CategoryDto getCategoryDto(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId)

				);

		log.info("user has been deleted");
		return this.entityToDto(cat);
	}

	@Override
	public List<CategoryDto> getAllCategoryDto() {
		List<Category> allCategory = this.categoryRepo.findAll();

		List<CategoryDto> allCatDto = allCategory.stream().map(category -> this.entityToDto(category))
				.collect(Collectors.toList());
		return allCatDto;
	}

	private Category dtoToEntity(CategoryDto CategoryDto) {
		ModelMapper modelMapper = new ModelMapper();
		Category category = modelMapper.map(CategoryDto, Category.class);
		return category;
	}

	private CategoryDto entityToDto(Category category) {
		ModelMapper modelMapper = new ModelMapper();
		CategoryDto CategoryDto = modelMapper.map(category, CategoryDto.class);
		return CategoryDto;
	}

}
