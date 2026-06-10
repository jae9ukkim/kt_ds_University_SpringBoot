package com.example.userservice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.service.UserService;
import com.example.userservice.vo.RegistUserVO;
import com.example.userservice.vo.ResponseUserVO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user-service")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/users")
	public ResponseEntity<ResponseUserVO> createUser(@RequestBody @Valid RegistUserVO registUserVO,
			BindingResult bindingResult) {
		// TODO 회원 가입 코드 작성.
		return null;
	}

	@GetMapping("/users")
	public ResponseEntity<List<ResponseUserVO>> getAllUsers() {
		// TODO 전체 사용자 조회 코드 작성
		return null;
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseUserVO> getOneUsers(@PathVariable String userId) {
		// TODO 회원 정보 조회 코드 작성
		return null;
	}

}
