package com.example.userservice.service;

import java.util.List;

import com.example.userservice.vo.RegistUserVO;
import com.example.userservice.vo.ResponseUserVO;

public interface UserService {

	ResponseUserVO createNewUser(RegistUserVO registUserVO);

	List<ResponseUserVO> fetchAllUsers();
	
	ResponseUserVO fetchOneUser(String userId);

}
