package com.eindopdracht_into_art.models.dtos;

import javax.validation.constraints.NotBlank;

public class AuthRequestDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    //region Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    //endregion

    //region Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //endregion
}
