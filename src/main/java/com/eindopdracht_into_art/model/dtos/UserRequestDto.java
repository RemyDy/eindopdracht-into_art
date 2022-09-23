package com.eindopdracht_into_art.model.dtos;

public class UserRequestDto {

    private String email;
    private String password;

    //region Getters

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    //endregion

    //region Setters

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //endregion
}


