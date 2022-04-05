package fun.kananika.blog.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.kananika.blog.entity.User;
import fun.kananika.blog.payloads.UserDTO;
import fun.kananika.blog.repositories.UserRepo;
import fun.kananika.blog.services.UserService;
import lombok.extern.slf4j.Slf4j;

/*
 *@Author Rabiroshan Shubhankar 
 * 
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		log.debug("createUser() is started in UserServiceImpl");
		User savedUser = this.userRepo.save(this.dtoToEntity(userDTO));
		return this.entityToDto(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Integer userId) {

		UserDTO userDTOData = this.entityToDto(this.userRepo.getById(userId));
		userDTOData.setAbout(userDTO.getAbout());
		userDTOData.setEmail(userDTO.getEmail());
		userDTOData.setName(userDTO.getName());
		userDTOData.setPassword(userDTO.getPassword());
		User savedUser = this.userRepo.save(this.dtoToEntity(userDTOData));
		return this.entityToDto(savedUser);
	}

	@Override
	public UserDTO getUserById(Integer userId) {

		User user = userRepo.getById(userId);

		return this.entityToDto(user);
	}

	@Override
	public List<UserDTO> getAllUser() {
		List<User> allUsers = userRepo.findAll();
		List<UserDTO> allUserDTO = new ArrayList<UserDTO>();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(allUsers, allUserDTO);
		return allUserDTO;
	}

	@Override
	public void deleteUser(Integer userId) {
		userRepo.deleteById(userId);
	}

	private User dtoToEntity(UserDTO userDTO) {
		ModelMapper modelMapper = new ModelMapper();
		User user = modelMapper.map(userDTO, User.class);
		return user;
	}

	private UserDTO entityToDto(User user) {
		ModelMapper modelMapper = new ModelMapper();
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}
}
