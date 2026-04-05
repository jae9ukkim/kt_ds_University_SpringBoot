package org.themoviedb.members.dao;

import org.apache.ibatis.annotations.Mapper;
import org.themoviedb.members.vo.MembersVO;
import org.themoviedb.members.vo.request.RegistVO;

@Mapper
public interface MembersDao {

    int insertNewMember(RegistVO registVO);

    MembersVO selectMemberByEmail(String email);

}
