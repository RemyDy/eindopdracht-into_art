package com.eindopdracht_into_art.controller.services.interfaces;

import com.eindopdracht_into_art.model.dtos.UserRequestDto;
import com.eindopdracht_into_art.model.dtos.UserDto;

public interface UserService {

    UserDto registerUser(UserRequestDto dto);
}
