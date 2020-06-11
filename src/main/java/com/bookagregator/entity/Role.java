package com.bookagregator.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, MODERATOR;


    @Override
    public String getAuthority() {
        return name();
    }
}
