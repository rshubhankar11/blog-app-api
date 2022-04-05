package fun.kananika.blog.services;
/*
 *@Author Rabiroshan Shubhankar 
 * 
 */

import java.util.List;

import fun.kananika.blog.payloads.UserDTO;

public interface UserService {

	UserDTO createUser(UserDTO userDTO);

	UserDTO updateUser(UserDTO userDTO, Integer userId);

	UserDTO getUserById(Integer userId);

	List<UserDTO> getAllUser();

	void deleteUser(Integer userId);
}
