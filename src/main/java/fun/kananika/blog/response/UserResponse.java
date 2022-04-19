package fun.kananika.blog.response;

import java.util.List;

import fun.kananika.blog.payloads.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

	String status;
	String message;
//	UserDTO user;
	List<UserDTO> users;
}
