package com.eindopdracht_into_art.model.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AuthRequestDto {

    @NotBlank
    @Email
    @Length(min = 5, max = 64)
    private String username;

    @NotBlank
    @Length(min = 8, max = 64)
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
