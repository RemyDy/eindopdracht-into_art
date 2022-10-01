package com.eindopdracht_into_art.services.interfaces;

import com.eindopdracht_into_art.models.dtos.UserDto;
import com.eindopdracht_into_art.models.dtos.RegistrationDto;

public interface UserService {

    UserDto registerUser(RegistrationDto dto);

    void addAuthority(Long id, String role);

    UserDto updateUser(UserDto dto);
}
