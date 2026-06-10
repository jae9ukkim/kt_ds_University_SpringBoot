package com.example.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userservice.dao.UserDao;
import com.example.userservice.vo.RegistUserVO;
import com.example.userservice.vo.ResponseUserVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public ResponseUserVO createNewUser(RegistUserVO registUserVO) {
		// TODO 회원 가입 서비스 코드 작성.
		return null;
	}

	@Override
	public List<ResponseUserVO> fetchAllUsers() {
		// TODO 전체 사용자 조회 코드 작성.
		return null;
	}

	@Override
	public ResponseUserVO fetchOneUser(String userId) {
		// TODO 회원 정보 조회 코드 작성.
		return null;
	}

}
