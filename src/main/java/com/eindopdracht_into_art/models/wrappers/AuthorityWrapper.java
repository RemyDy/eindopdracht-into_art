package com.eindopdracht_into_art.models.wrappers;

import com.eindopdracht_into_art.models.entities.Authority;
import org.springframework.security.core.GrantedAuthority;


public class AuthorityWrapper implements GrantedAuthority {

    private final Authority authority;

    public AuthorityWrapper(Authority authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority.getAuthority();
    }
}
