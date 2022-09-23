package com.eindopdracht_into_art.controller.services;

import com.eindopdracht_into_art.controller.repositories.UserRepository;
import com.eindopdracht_into_art.model.entities.SecurityUser;
import com.eindopdracht_into_art.model.exceptions.RecordNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = userRepository.findUserByUsername(username);

        return user.map(SecurityUser::new)
                .orElseThrow(RecordNotFoundException::new);
    }
}
