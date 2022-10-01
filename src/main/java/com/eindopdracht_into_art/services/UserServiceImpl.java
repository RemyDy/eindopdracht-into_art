package com.eindopdracht_into_art.services;

import com.eindopdracht_into_art.models.dtos.UserDto;
import com.eindopdracht_into_art.models.dtos.RegistrationDto;
import com.eindopdracht_into_art.models.entities.Authority;
import com.eindopdracht_into_art.models.entities.User;
import com.eindopdracht_into_art.models.exceptions.RecordExistsException;
import com.eindopdracht_into_art.models.exceptions.RecordNotFoundException;
import com.eindopdracht_into_art.repositories.UserRepository;
import com.eindopdracht_into_art.services.interfaces.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository repository,
            BCryptPasswordEncoder passwordEncoder
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto registerUser(RegistrationDto dto) {
        final var userRecord = repository.findUserByUsername(dto.getUsername());

        if (userRecord.isPresent()) {
            throw new RecordExistsException("username '%s' is al in gebruik".formatted(dto.getUsername()));
        }
        final var user = relayToUser(dto);
        final var savedUser = repository.save(user);
        return relayToDto(savedUser);
    }

    @Override
    public void addAuthority(Long id, String role) {
        final var user = repository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
        try {
            user.addAuthorities(new Authority(id, role));
            repository.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto updateUser(UserDto dto) {
        return null;
    }

    private UserDto relayToDto(User savedUser) {
        final var dto = new UserDto();
        dto.setId(savedUser.getId());
        dto.setUsername(savedUser.getUsername());
        dto.setAuthorities(savedUser.getAuthorities());
        dto.setEnabled(savedUser.isEnabled());
        dto.setLocked(savedUser.isLocked());
        dto.setEmail(savedUser.getEmail());
        return dto;
    }

    private User relayToUser(RegistrationDto dto) {
        final var user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setLocked(false);
        user.setEnabled(true);
        return user;
    }

}
