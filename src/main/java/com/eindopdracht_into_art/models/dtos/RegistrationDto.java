package com.eindopdracht_into_art.models.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class RegistrationDto {

    @Length(min = 2, max = 64,
            message = "Invoer naam is ongeldig, minimaal 2 en maximaal 64 karakters invullen.")
    private String username;

    @NotBlank
    @Pattern(regexp = "^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$",
            message = "Invoer password is ongeldig")
    private String password;

    @Email
    private String email;

    //region Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
    //endregion

    //region Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //endregion
}
