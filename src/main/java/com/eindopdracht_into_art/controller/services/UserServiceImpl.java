package com.eindopdracht_into_art.controller.services;

import com.eindopdracht_into_art.controller.repositories.UserRepository;
import com.eindopdracht_into_art.controller.services.interfaces.UserService;
import com.eindopdracht_into_art.model.dtos.UserRequestDto;
import com.eindopdracht_into_art.model.dtos.UserDto;
import com.eindopdracht_into_art.model.entities.User;
import com.eindopdracht_into_art.model.exceptions.RecordExistsException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDto registerUser(UserRequestDto dto) {
        final var userRecord = repository.findUserByUsername(dto.getEmail());

        if (userRecord.isPresent()) {
            throw new RecordExistsException("%s is al in gebruik".formatted(dto.getEmail()));
        }
        final var user = relayToUser(dto);
        final var savedUser = repository.save(user);

        return relayUserDto(savedUser);
    }

    private UserDto relayUserDto(User savedUser) {
        final var dto = new UserDto();
        dto.setId(savedUser.getId());
        dto.setUsername(savedUser.getUsername());
        return dto;
    }

    private User relayToUser(UserRequestDto dto) {
        final var user = new User();
        user.setUsername(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

}
