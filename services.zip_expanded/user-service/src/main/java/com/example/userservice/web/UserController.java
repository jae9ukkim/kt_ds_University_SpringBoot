package com.example.userservice.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.exceptions.UserException;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.RegistUserVO;
import com.example.userservice.vo.ResponseUserVO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user-service")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@PostMapping("/users")
	public ResponseEntity<ResponseUserVO> createUser(@RequestBody @Valid RegistUserVO registUserVO,
			BindingResult bindingResult) {
		// 회원 가입 코드 작성.
		if(bindingResult.hasErrors()) {
			throw new UserException(bindingResult.getFieldErrors());
		}
		ResponseUserVO responseUser = userService.createNewUser(registUserVO);
		return new ResponseEntity<ResponseUserVO>(responseUser, HttpStatusCode.valueOf(201));
	}

	@GetMapping("/users")
	public ResponseEntity<List<ResponseUserVO>> getAllUsers(@RequestHeader("USER_ID") String userId,
			@RequestHeader("ROLES") String roles) {
		logger.debug("userId: {}", userId);
		logger.debug("roles: {}", roles);
		
		if(!roles.equals("ROLE_ADMIN")) {
			// Method Not Allowed
			return new ResponseEntity<>(HttpStatusCode.valueOf(405));
		}
		// 전체 사용자 조회 코드 작성
		List<ResponseUserVO> responseUser = userService.fetchAllUsers();
		return new ResponseEntity<List<ResponseUserVO>>(responseUser, HttpStatusCode.valueOf(200));
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseUserVO> getOneUsers(@PathVariable String userId, @RequestHeader("USER_ID") String authUserId) {

		if(!userId.equals(authUserId)) {
			// Method Not Allowed
			return new ResponseEntity<>(HttpStatusCode.valueOf(405));
		}
		// 회원 정보 조회 코드 작성
		ResponseUserVO responseUser = userService.fetchOneUser(userId);
		return new ResponseEntity<ResponseUserVO>(responseUser, HttpStatusCode.valueOf(200));
	}

}
