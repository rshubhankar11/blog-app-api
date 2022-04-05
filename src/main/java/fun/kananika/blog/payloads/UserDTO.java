package fun.kananika.blog.payloads;

import lombok.Data;

/*
 * @Author Rabiroshan Shubhankar
 * 
 * DTO for user entity
 */

@Data
public class UserDTO {

	private Integer id;
	private String name;
	private String email;
	private String password;
	private String about;

}
