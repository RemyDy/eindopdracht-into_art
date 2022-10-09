package com.eindopdracht_into_art.services;

import com.eindopdracht_into_art.models.exceptions.RecordNotFoundException;
import com.eindopdracht_into_art.models.wrappers.UserDetailsWrapper;
import com.eindopdracht_into_art.repositories.UserAccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserAccountRepository repository;

    public UserDetailsServiceImpl(UserAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        final var user = repository.findUserByUsername(username);
        return user.map(UserDetailsWrapper::new).orElseThrow(()-> new RecordNotFoundException(username));
    }

}
