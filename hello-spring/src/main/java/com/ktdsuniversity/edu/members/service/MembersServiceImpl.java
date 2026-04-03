package com.ktdsuniversity.edu.members.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.members.helpers.SHA256Util;
import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.members.vo.request.RegistVO;
import com.ktdsuniversity.edu.members.vo.request.UpdateVO;
import com.ktdsuniversity.edu.members.vo.response.SearchResultVO;

@Service
public class MembersServiceImpl implements MembersService {

	@Autowired
	private MembersDao membersDao;
	
	@Override
	public boolean createNewMember(RegistVO registVO) {
		
		MembersVO membersVO = this.membersDao.selectMemberByEmail(registVO.getEmail());
		if(membersVO != null) {
			throw new IllegalArgumentException(registVO.getEmail() + "은 이미 사용중입니다.");
		}
		
		// 암호화를 위한 비밀키 생성
		String newSalt = SHA256Util.generateSalt();
		String usersPassword = registVO.getPassword();
		
		// 사용자가 입력한 비밀번호를 newSalt를 이용해 암호화
		// 비밀번호와 newSalt의 값이 일치하면, 항상 같은 값의 암호화 결과가 생성된다.
		usersPassword = SHA256Util.getEncrypt(usersPassword, newSalt);
		
		// 비밀키 저장
		registVO.setSalt(newSalt);
		// 암호화된 비밀번호 저장
		registVO.setPassword(usersPassword);
		
		
		int insertCount = membersDao.insertNewMember(registVO);
		System.out.println("생성된 계정의 개수? " + insertCount);
		
		return insertCount == 1;
	}

	@Override
	public MembersVO findMemberByEmail(String email) {
		return this.membersDao.selectMemberByEmail(email);
	}

	@Override
	public boolean updateMemberByEmail(UpdateVO updateVO) {
		int updateCount = this.membersDao.updateMemberByEmail(updateVO);
		return updateCount == 1;
	}

	@Override
	public boolean deleteMemberByEmail(String email) {
		int deleteCount = this.membersDao.deleteMemberByEmail(email);
		return deleteCount == 1;
	}

	@Override
	public SearchResultVO findMembersAll() {
		
		SearchResultVO searchResult = new SearchResultVO();
		
		int count = this.membersDao.selectMembersCount();
		searchResult.setCount(count);
		
		if(count == 0) {
			return searchResult;
		}
		
		List<MembersVO> list = this.membersDao.selectMembersList();
		searchResult.setMembersVO(list);
		
		return searchResult;
	}

}
