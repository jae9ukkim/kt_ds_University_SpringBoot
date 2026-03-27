package com.ktdsuniversity.edu.members.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.members.vo.request.MembersVO;

@Mapper
public interface MembersDao {

	int insertNewMember(MembersVO membersVO);

}
