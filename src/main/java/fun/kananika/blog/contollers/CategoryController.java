package fun.kananika.blog.contollers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.kananika.blog.payloads.CategoryDto;
import fun.kananika.blog.response.ApiResponse;
import fun.kananika.blog.services.CategoryService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/category/v1")
public class CategoryController {

	@Autowired
	private CategoryService categorryService;

//	Create
	@PostMapping("/addCategory")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto createCategory = this.categorryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
	}

//	Update
	@PutMapping("/updateCategory/{catID}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer catID) {
		CategoryDto updatedCategory = this.categorryService.updateCategory(categoryDto, catID);
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
	}

//	delete
	@DeleteMapping("/deleteCategory/{catID}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catID) {
		this.categorryService.deleteCategory(catID);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully ", "True"),
				HttpStatus.OK);
	}

//	get by id
	@GetMapping("/getCategory/{catID}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catID) {
		CategoryDto categoryDto = this.categorryService.getCategoryDto(catID);
		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
	}

//	get all
	@GetMapping("/getAllCategory")
	public ResponseEntity<List<CategoryDto>> getAllACategory() {
		List<CategoryDto> allCategoryDto = this.categorryService.getAllCategoryDto();
		return new ResponseEntity<List<CategoryDto>>(allCategoryDto, HttpStatus.OK);
	}

}
