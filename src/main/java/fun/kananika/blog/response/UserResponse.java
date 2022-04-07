package fun.kananika.blog.response;

import java.util.List;

import fun.kananika.blog.payloads.UserDTO;
import lombok.Data;

@Data
public class UserResponse {

	String status;
	String message;
//	UserDTO user;
	List<UserDTO> users;
}
