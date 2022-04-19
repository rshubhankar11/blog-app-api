package fun.kananika.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.Data;

/*
 * @Author Rabiroshan Shubhankar
 * 
 * DTO for user entity
 */

@Data
public class UserDTO {

	private Integer id;
	@NotEmpty
	@Size(min = 4, message = "Username must be of more than 4 characters")
	private String name;
	@Email(message = "Email address is not valid!!")
	private String email;
	@NotEmpty
	@Size(min = 3 , max = 10, message = "Password must be min 3 / max 10 char")
//	@Pattern(regexp = )
	private String password;

	@NotEmpty
	private String about;

}
