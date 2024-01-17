package com.pinterestclonebackend.demo.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomToken extends UsernamePasswordAuthenticationToken {

    private Long userId;

    public CustomToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public CustomToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, Long userId) {
        super(principal, credentials, authorities);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
