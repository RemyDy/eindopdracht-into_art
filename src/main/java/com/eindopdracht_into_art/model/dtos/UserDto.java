package com.eindopdracht_into_art.model.dtos;

public class UserDto {

    private Long id;

    private String username;


    //region Getters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }


    //endregion

    //region Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //endregion
}
