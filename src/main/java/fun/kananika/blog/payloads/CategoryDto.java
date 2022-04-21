package fun.kananika.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {

	private Integer categoryId;
	
	@NotBlank
	@Size(min = 4,message = "Need to give minimum 4char")
	private String categoryTitle;
	
	@NotBlank
	@Size(min = 10,message = "Need to give minimum 10 char")
	private String categoryDescription;
}
