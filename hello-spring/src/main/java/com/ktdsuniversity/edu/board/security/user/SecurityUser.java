package com.ktdsuniversity.edu.board.security.user;

import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ktdsuniversity.edu.members.vo.MembersVO;

public class SecurityUser implements UserDetails{

    /**
     * 
     */
    private static final long serialVersionUID = -2678764300436454681L;
    
    private MembersVO membersVO;

    public SecurityUser(MembersVO membersVO) {
        this.membersVO = membersVO;
    }
    

    public MembersVO getMembersVO() {
        return this.membersVO;
    }


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
        // TODO Auto-generated method stub
        return this.membersVO.getEmail();
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return this.membersVO.getBlockYn().equals("N");
    }

}
