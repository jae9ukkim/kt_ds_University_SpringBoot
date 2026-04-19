package com.ktdsuniversity.edu.board.security.provider;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ktdsuniversity.edu.board.security.authenticate.service.SecurityPasswordEncoder;
import com.ktdsuniversity.edu.board.security.user.SecurityUser;
import com.ktdsuniversity.edu.members.vo.MembersVO;

public class UsernameAndPasswordAuthenticationProvider implements AuthenticationProvider {
    
    private UserDetailsService userDetailService;
    private PasswordEncoder passwordEncoder;

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // TODO Auto-generated method stub
        String email = authentication.getName();
        
        UserDetails userDetails =  this.userDetailService.loadUserByUsername(email);
        if(!userDetails.isAccountNonLocked()) {
            throw new LockedException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        
        String rawPassword = authentication.getCredentials().toString();
        MembersVO membersVO = ((SecurityUser)userDetails).getMembersVO();
        
        SecurityPasswordEncoder passwordComparator = (SecurityPasswordEncoder)this.passwordEncoder;
        
        boolean isMatch = passwordComparator.matches(rawPassword, membersVO.getSalt(), membersVO.getPassword());
        if(!isMatch) {
            throw new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        
        return new UsernamePasswordAuthenticationToken(membersVO, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
