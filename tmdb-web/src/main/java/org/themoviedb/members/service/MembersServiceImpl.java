package org.themoviedb.members.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.themoviedb.members.dao.MembersDao;
import org.themoviedb.members.helpers.SHA256Util;
import org.themoviedb.members.vo.MembersVO;
import org.themoviedb.members.vo.request.RegistVO;

@Service
public class MembersServiceImpl implements MembersService {

	@Autowired
	private MembersDao membersDao;

    @Override
    public boolean createNewMember(RegistVO registVO) {
        
        MembersVO membersVO = this.membersDao.selectMemberByEmail(registVO.getEmail());
        if(membersVO != null) {
            throw new IllegalArgumentException(registVO.getEmail() + "은 이미 사용 중 입니다.");
        }
        
        String salt = SHA256Util.generateSalt();
        String password = SHA256Util.getEncrypt(registVO.getPassword(), salt);
        
        registVO.setSalt(salt);
        registVO.setPassword(password);

        int insertResult = membersDao.insertNewMember(registVO);
        
        return insertResult == 1;
    }

    @Override
    public MembersVO findMemberByEmail(String email) {
        
        return this.membersDao.selectMemberByEmail(email);
    }
}
