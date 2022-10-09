package com.eindopdracht_into_art.models.dtos;

import com.eindopdracht_into_art.models.entities.Authority;

import java.util.Set;

public class UserAccountDto {

    private Long id;

    private String username;

    private String email;

    private Set<Authority> authorities;

    private boolean locked;

    private boolean enabled;

    //region Getters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isLocked() {
        return locked;
    }

    public boolean isEnabled(boolean enabled) {
        return this.enabled;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    //endregion

    //region Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    //endregion
}
