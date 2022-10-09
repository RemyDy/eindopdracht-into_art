package com.eindopdracht_into_art.services.interfaces;

import com.eindopdracht_into_art.models.dtos.UserAccountDto;
import com.eindopdracht_into_art.models.dtos.RegistrationDto;

public interface UserAccountService {

    UserAccountDto registerUser(RegistrationDto dto);

    void addAuthority(Long id, String role);

    UserAccountDto updateUserAccount(UserAccountDto dto);

    UserAccountDto getUserByUsername(UserAccountDto dto);
}
