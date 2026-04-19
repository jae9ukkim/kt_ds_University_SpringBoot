package com.ktdsuniversity.edu.board.security.authenticate.service;

import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ktdsuniversity.edu.members.helpers.SHA256Util;

public class SecurityPasswordEncoder implements PasswordEncoder{

    @Override
    public @Nullable String encode(@Nullable CharSequence rawPassword) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean matches(@Nullable CharSequence rawPassword, @Nullable String encodedPassword) {
        // TODO Auto-generated method stub
        return false;
    }

    public String encode(String rawPassword, String salt) {
        return SHA256Util.getEncrypt(rawPassword, salt);
    }

    public boolean matches(String rawPassword, String salt, String encodedPassword) {
        return this.encode(rawPassword, salt).equals(encodedPassword);
    }

}
