package com.eindopdracht_into_art.services;

import com.eindopdracht_into_art.models.exceptions.RecordNotFoundException;
import com.eindopdracht_into_art.models.wrappers.UserDetailsWrapper;
import com.eindopdracht_into_art.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        final var user = repository.findUserByUsername(username);
        return user.map(UserDetailsWrapper::new).orElseThrow(()-> new RecordNotFoundException(username));
    }
//    public UserDetailsServiceImpl(UserRepository userRepository, UserService userService) {
//        this.userService = userService;
//        this.userRepository = userRepository;
//    }

    //        final var user = userService.findUserByUsername(username);
//        return user.map(UserDetailsWrapper::new)
//                .orElseThrow(RecordNotFoundException::new);

//    UserDetails relayToUserDetailsUser(UserDto dto) {
//        Set<Authority> authorities = dto.getRole();
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for (Authority authority: authorities){
//            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
//        }
//        return new User(dto.getUsername(), dto.getUsername(), grantedAuthorities);
//    }

}
