package com.example.security;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    INTERNAL_API_USER,
    EXTERNAL_API_USER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
