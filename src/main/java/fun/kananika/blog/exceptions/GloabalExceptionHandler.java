package fun.kananika.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import fun.kananika.blog.response.UserResponse;

@RestControllerAdvice
public class GloabalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<UserResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message = ex.getMessage();
		UserResponse userResponse = new UserResponse();
		userResponse.setMessage(message);
		userResponse.setStatus("false");
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String , String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		Map<String , String> response = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error->{
			String field = ((FieldError)error).getField();
			String defaultMessage = error.getDefaultMessage();
			response.put(field, defaultMessage);
		});
		return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
	}
}
