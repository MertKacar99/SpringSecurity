package com.myTokenApp.tokenapp.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_MOD("ROLE_MOD");

    private String value;

    Role(String value){
        this.value = value;
    }

    @Override
    public String getAuthority() {
        return value;
    }
}
