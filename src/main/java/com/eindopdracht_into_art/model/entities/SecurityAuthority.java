package com.eindopdracht_into_art.model.entities;

import org.springframework.security.core.GrantedAuthority;


public class SecurityAuthority implements GrantedAuthority {

    private final Authority authority;

    public SecurityAuthority(Authority authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority.getName();
    }
}
