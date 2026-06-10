package com.example.userservice.exceptions.handlers;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.userservice.exceptions.UserException;

public class UserExceptionHandler {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<? extends Object> handleUserException(UserException userException) {
		// TODO Exception Code 작성.
		return null;
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException runtimeException) {
		return new ResponseEntity<String>("Internal Server Error", HttpStatusCode.valueOf(500));
	}

}
