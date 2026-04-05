package org.themoviedb.members.service;

import org.themoviedb.members.vo.MembersVO;
import org.themoviedb.members.vo.request.RegistVO;

public interface MembersService {

    boolean createNewMember(RegistVO registVO);

    MembersVO findMemberByEmail(String email);

}
