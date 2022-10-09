package com.eindopdracht_into_art.services;

import com.eindopdracht_into_art.models.dtos.UserAccountDto;
import com.eindopdracht_into_art.models.dtos.RegistrationDto;
import com.eindopdracht_into_art.models.entities.Authority;
import com.eindopdracht_into_art.models.entities.UserAccount;
import com.eindopdracht_into_art.models.exceptions.RecordExistsException;
import com.eindopdracht_into_art.models.exceptions.RecordNotFoundException;
import com.eindopdracht_into_art.repositories.UserAccountRepository;
import com.eindopdracht_into_art.services.interfaces.UserAccountService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserAccountServiceImpl(
            UserAccountRepository repository,
            BCryptPasswordEncoder passwordEncoder
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserAccountDto registerUser(RegistrationDto dto) {
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
            user.addAuthorities(new Authority(role));
            repository.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserAccountDto updateUserAccount(UserAccountDto dto) {
        return null;
    }

    @Override
    public UserAccountDto getUserByUsername(UserAccountDto dto) {
        final var User = repository.findUserByUsername(dto.getUsername())
                .orElseThrow(RecordNotFoundException::new);
        return relayToDto(User);
    }

    //region Private methods
    private UserAccountDto relayToDto(UserAccount savedUserAccount) {
        final var dto = new UserAccountDto();
        dto.setId(savedUserAccount.getId());
        dto.setUsername(savedUserAccount.getUsername());
        dto.setAuthorities(savedUserAccount.getAuthorities());
        dto.setEnabled(savedUserAccount.isEnabled());
        dto.setLocked(savedUserAccount.isLocked());
        dto.setEmail(savedUserAccount.getEmail());
        return dto;
    }

    private UserAccount relayToUser(RegistrationDto dto) {
        final var user = new UserAccount();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setLocked(false);
        user.setEnabled(true);
        return user;
    }
    //endregion

}
