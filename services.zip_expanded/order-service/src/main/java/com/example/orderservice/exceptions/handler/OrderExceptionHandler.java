package com.example.orderservice.exceptions.handler;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.orderservice.exceptions.OrderException;

@RestControllerAdvice
public class OrderExceptionHandler {

	@ExceptionHandler(OrderException.class)
	public ResponseEntity<? extends Object> handleUserException(OrderException orderException) {
		// TODO 예외 처리 코드 작성.
		return null;
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException runtimeException) {
		return new ResponseEntity<String>("Internal Server Error", HttpStatusCode.valueOf(500));
	}

}
