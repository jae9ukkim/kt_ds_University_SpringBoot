package com.example.itemservice.exceptions.handler;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.itemservice.exceptions.ItemException;

@RestControllerAdvice
public class ItemExceptionHandler {

	@ExceptionHandler(ItemException.class)
	public ResponseEntity<? extends Object> handleUserException(ItemException itemException) {
		// TODO 예외 메시지 코드 작성.
		return null;
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException runtimeException) {
		return new ResponseEntity<String>("Internal Server Error", HttpStatusCode.valueOf(500));
	}

}
