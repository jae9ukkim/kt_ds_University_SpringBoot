package com.ktdsuniversity.edu.board.security.user;

import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ktdsuniversity.edu.members.vo.MembersVO;

public class SecurityUser implements UserDetails{

    /**
     * Spring Security가 사용자를 식별할 때 사용
     */
    private static final long serialVersionUID = -2678764300436454681L;
    
    /**
     * UserDetails 인터페이스로 사용자의 세부 내용을 알 수 없기 때문에
     * 사용자의 정보를 가지고 있는 membersVO를 멤버변수로 추가해준다.
     */
    private MembersVO membersVO;

    public SecurityUser(MembersVO membersVO) {
        this.membersVO = membersVO;
    }
    

    public MembersVO getMembersVO() {
        return this.membersVO;
    }

    /**
     * 사용자의 권한 목록을 관리
     * ROLES 테이블에서 조회
     * 
     * GrantedAuthority <-- 사용자에게 허용된 권한     * 
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return membersVO.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_"+role))
                        .toList();
    }

    @Override
    public @Nullable String getPassword() {
        return this.membersVO.getPassword();
    }

    @Override
    public String getUsername() {
        return this.membersVO.getEmail();
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return this.membersVO.getBlockYn().equals("N");
    }

}
