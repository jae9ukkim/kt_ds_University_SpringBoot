package com.ktdsuniversity.edu.members.service;


import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.members.vo.request.LoginVO;
import com.ktdsuniversity.edu.members.vo.request.RegistVO;
import com.ktdsuniversity.edu.members.vo.request.UpdateVO;
import com.ktdsuniversity.edu.members.vo.response.SearchResultVO;

import jakarta.validation.Valid;

public interface MembersService {

	public boolean createNewMember(RegistVO registVO);

	public MembersVO findMemberByEmail(String email);

	public boolean updateMemberByEmail(UpdateVO updateVO);

	public boolean deleteMemberByEmail(String email);

	public SearchResultVO findMembersAll();

	public MembersVO findMemberByEmailAndPassword(@Valid LoginVO loginVO);
	
}
