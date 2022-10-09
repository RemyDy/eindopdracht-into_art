package com.eindopdracht_into_art.models.wrappers;

import com.eindopdracht_into_art.models.entities.UserAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class UserDetailsWrapper implements UserDetails {

    private final UserAccount userAccount;

    public UserDetailsWrapper(UserAccount userAccount)  {
        this.userAccount = userAccount;
    }

    @Override
    public String getUsername() {
        return userAccount.getUsername();
    }

    @Override
    public String getPassword() {
        return userAccount.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userAccount.getAuthorities()
                .stream()
                .map(AuthorityWrapper::new)
                .toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !userAccount.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userAccount.isEnabled();
    }
}
