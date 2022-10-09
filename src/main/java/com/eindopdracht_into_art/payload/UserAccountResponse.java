package com.eindopdracht_into_art.payload;

import com.eindopdracht_into_art.models.dtos.UserAccountDto;

public record UserAccountResponse(UserAccountDto userByUsername) {

    public Long getUserId() {
        return userByUsername.getId();
    }

}
