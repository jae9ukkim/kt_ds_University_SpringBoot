package com.ktdsuniversity.edu.board.security.authenticate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ktdsuniversity.edu.board.security.user.SecurityUser;
import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.members.vo.MembersVO;

@Component
public class SecurityUserDetailsService implements UserDetailsService{
    
    @Autowired
    private MembersDao membersDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 아이디로 회원정보 DB에서 조회
        MembersVO loadedUser = this.membersDao.selectMemberByEmail(username);
        
        if(loadedUser == null) {
            throw new UsernameNotFoundException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        
        // 권한 DB에서 조회
        List<String> userRole = this.membersDao.selectMemberRolesByEmail(username);
        loadedUser.setRoles(userRole);
        
        return new SecurityUser(loadedUser);
    }

}
