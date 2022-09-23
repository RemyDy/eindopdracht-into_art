package com.eindopdracht_into_art.model.dtos;

public class UserAuthResponseDto {

     private String email;
     private String accessToken;

    //region Getters
    public String getEmail() {
        return email;
    }

    public String getAccessToken() {
        return accessToken;
    }
    //endregion

    //region Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    //endregion
}
