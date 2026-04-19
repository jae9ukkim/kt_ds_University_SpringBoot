package com.ktdsuniversity.edu.board.security.authenticate.service;

import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ktdsuniversity.edu.members.helpers.SHA256Util;

/**
 * 데이터베이스에 있는 비밀번호와 로그인 요청 정보의 비밀번호가 일치하는지 검사
 * 
 * 필요한 데이터
 * 1. 데이터베이스의 회원 비밀번호(암호화되어 있는 비밀번호)
 * 2. 로그인 요청 정보 중 비밀번호(암호화되어있지 않은 비밀번호)
 * 3. 로그인 요청 정보 중 비밀번호를 암호화 하기 위한 salt 정보
 */
@Component
public class SecurityPasswordEncoder implements PasswordEncoder{

    @Override
    public @Nullable String encode(@Nullable CharSequence rawPassword) {
        return null;
    }

    @Override
    public boolean matches(@Nullable CharSequence rawPassword, @Nullable String encodedPassword) {
        return false;
    }

    /**
     * 로그인 요청 정보 중 평문 비밀번호를 암호화 하는 코드
     */
    public String encode(String rawPassword, String salt) {
        return SHA256Util.getEncrypt(rawPassword, salt);
    }

    /**
     * 로그인 요청정보 중 평문 비밀번호와 데이터베이스에 있는 암호화된 비밀번호가 일치하는지 검사
     * 평문 비밀번호 ==> 암호화 ==> 데이터베이스의 암호와 비교
     */
    public boolean matches(String rawPassword, String salt, String encodedPassword) {
        return this.encode(rawPassword, salt).equals(encodedPassword);
    }

}
