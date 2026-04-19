package com.ktdsuniversity.edu.board.security.provider;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ktdsuniversity.edu.board.security.authenticate.service.SecurityPasswordEncoder;
import com.ktdsuniversity.edu.board.security.user.SecurityUser;
import com.ktdsuniversity.edu.members.vo.MembersVO;

/**
 * Spring Security의 인증(아이디와 비밀번호 일치 검사)을 수행하는 공급자
 * 사용자의 인증정보가 일치할 경우 Authentication Token을 발급해 SecurityContext에 저장하도록 한다
 */
@Component
public class UsernameAndPasswordAuthenticationProvider implements AuthenticationProvider {
    
    @Autowired
    private UserDetailsService userDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 사용자로부터 Spring Security 로그인 요청이 있을 때 마다 실행
     * 
     * 사용자가 보내준 아이디와 비밀번호를 이용해 인증을 수행한다.
     * UserDetailsService 인터페이스를 이용해 사용자의 정보를 조회하고
     * PasswordEncoder 인터페이스를 이용해 사용자의 비밀번호를 검증하고
     * 인증정보가 일치할 때만 UsernamePasswordAuthenticationToken을 발급한다.
     * 
     * @param authentication : 사용자가 로그인 요청한 정보 (아이디, 비밀번호)
     * @return UsernamePasswordAuthenticationToken
     */
    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // 로그인에 사용된 사용자의 아이디(이메일)
        String email = authentication.getName();
        
        // UserDetails ==> SecurityUser
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
        
        // SecurityContext에 저장할 인증 토큰
        return new UsernamePasswordAuthenticationToken(membersVO, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
