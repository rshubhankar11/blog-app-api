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

import fun.kananika.blog.payloads.UserDTO;
import fun.kananika.blog.response.UserResponse;
import fun.kananika.blog.services.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user/v1")
public class UserController {

	@Autowired
	private UserService userService;

//	POST - create user
	@PostMapping("/create")
	public ResponseEntity<UserResponse> creatUser( @Valid @RequestBody UserDTO user) {
		UserDTO createdUser = this.userService.createUser(user);

		UserResponse userResponse = new UserResponse();
		userResponse.setMessage("User created");
		userResponse.setStatus("SUCCESS");
		userResponse.setUsers(List.of(createdUser));

		return ResponseEntity.ok(userResponse);

	}

//	PUT - update user 
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserDTO user, @PathVariable Integer userId) {

		UserResponse userResponse = null;
		UserDTO updatedUser = this.userService.updateUser(user, userId);

		userResponse = new UserResponse();
		userResponse.setMessage("User updated");
		userResponse.setStatus("SUCCESS");
		userResponse.setUsers(List.of(updatedUser));
		try {
//			UserDTO updatedUser = this.userService.updateUser(user, userId);
//
//			userResponse = new UserResponse();
//			userResponse.setMessage("User updated");
//			userResponse.setStatus("SUCCESS");
//			userResponse.setUsers(List.of(updatedUser));
		} catch (Exception e) {
			userResponse = new UserResponse();
			userResponse.setMessage("User Id is not valid");
			userResponse.setStatus("ERROR");
			log.error("Error in updateUser() ...For User{}",e,userId);
			return new ResponseEntity<>(
			          userResponse, 
			          HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok(userResponse);

	}

//	DELETE - Delete user 
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<UserResponse> deleteUser(@PathVariable Integer userId) {

		this.userService.deleteUser(userId);

		UserResponse userResponse = null;
		try {
			userResponse = new UserResponse();
			userResponse.setMessage("User Deleted");
			userResponse.setStatus("SUCCESS");
		} catch (Exception e) {
			userResponse = new UserResponse();
			userResponse.setMessage("User Id is not valid");
			userResponse.setStatus("ERROR");
			log.error("Excepting occour in get user by id{} ",e,userId);
			return new ResponseEntity<>(
			          userResponse, 
			          HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok(userResponse);
	}
	@DeleteMapping("/deleteUserName/{name}")
	public ResponseEntity<UserResponse> deleteUser(@PathVariable String name) {

		this.userService.deleteByUserName(name);

		UserResponse userResponse = new UserResponse();
		userResponse.setMessage("User Deleted");
		userResponse.setStatus("SUCCESS");

		return ResponseEntity.ok(userResponse);
	}

//	GET - User get
	@GetMapping("/allUser")
	public ResponseEntity<UserResponse> allUsers() {

		List<UserDTO> allUser = this.userService.getAllUser();

		UserResponse userResponse = new UserResponse();
		userResponse.setMessage("All user fatched");
		userResponse.setStatus("SUCCESS");
		userResponse.setUsers(allUser);

		return ResponseEntity.ok(userResponse);
	}

	@GetMapping("/userById/{userId}")
	public ResponseEntity<UserResponse> userById(@PathVariable Integer userId) {

		UserResponse userResponse = null;
		UserDTO userById = this.userService.getUserById(userId);

		userResponse = new UserResponse();
		userResponse.setMessage("All user fatched");
		userResponse.setStatus("SUCCESS");
		userResponse.setUsers(List.of(userById));

		try {
//			UserDTO userById = this.userService.getUserById(userId);
//
//			userResponse = new UserResponse();
//			userResponse.setMessage("All user fatched");
//			userResponse.setStatus("SUCCESS");
//			userResponse.setUsers(List.of(userById));

		} catch (Exception e) {
			userResponse = new UserResponse();
			userResponse.setMessage("User Id is not valid");
			userResponse.setStatus("ERROR");
			log.error("Excepting occour in get user by id{} ",e,userId);
			return new ResponseEntity<>(
			          userResponse, 
			          HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return ResponseEntity.ok(userResponse);
	}

}
