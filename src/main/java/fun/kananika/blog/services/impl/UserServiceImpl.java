package fun.kananika.blog.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.kananika.blog.entity.User;
import fun.kananika.blog.exceptions.ResourceNotFoundException;
import fun.kananika.blog.payloads.UserDTO;
import fun.kananika.blog.repositories.UserRepo;
import fun.kananika.blog.services.UserService;
import fun.kananika.blog.exceptions.ResourceNotFoundException;
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
		log.debug("createUser() is ended in UserServiceImpl");
		return this.entityToDto(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Integer userId) {
		log.debug("updateUser() is started in UserServiceImpl");
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userID", userId));
		user.setAbout(userDTO.getAbout());
		user.setEmail(userDTO.getEmail());
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());
		User savedUser = this.userRepo.save(user);
		log.debug("updateUser() is ended in UserServiceImpl");
		return this.entityToDto(savedUser);
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		log.debug("getUserById() is started in UserServiceImpl");

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userID", userId));
		log.debug("getUserById() is ended in UserServiceImpl");
		return this.entityToDto(user);
	}

	@Override
	public List<UserDTO> getAllUser() {
		List<User> allUsers = userRepo.findAll();
		log.debug("getAllUser() is started in UserServiceImpl");
		List<UserDTO> allUserDTO = allUsers.stream().map(user -> this.entityToDto(user)).collect(Collectors.toList());
		log.debug("getAllUser() is ended in UserServiceImpl");
		return allUserDTO;
	}

	@Override
	public void deleteUser(Integer userId) {
		log.debug("deleteUser() is started in UserServiceImpl");

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userID", userId));
		userRepo.delete(user);
		log.debug("deleteUser() is ended in UserServiceImpl");
	}

	@Override
	public void deleteByUserName(String name) {
		this.userRepo.deleteByName(name);


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
