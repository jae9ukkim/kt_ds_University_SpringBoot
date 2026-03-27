package com.ktdsuniversity.edu.members.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.members.vo.request.MembersVO;

@Service
public class MembersServiceImpl implements MembersService {

	@Autowired
	private MembersDao membersDao;
	
	@Override
	public boolean createNewMember(MembersVO membersVO) {
		
		int insertCount = membersDao.insertNewMember(membersVO);
		System.out.println("생성된 계정의 개수? " + insertCount);
		
		return insertCount == 1;
	}

}
