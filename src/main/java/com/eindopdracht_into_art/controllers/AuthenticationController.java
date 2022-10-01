package com.eindopdracht_into_art.controllers;

import com.eindopdracht_into_art.config.security.JwtService;
import com.eindopdracht_into_art.models.dtos.AuthRequestDto;
import com.eindopdracht_into_art.payload.AuthenticationResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.eindopdracht_into_art.config.security.SecurityConstants.PREFIX_BEARER;
import static com.eindopdracht_into_art.controllers.endpoints.ControllerEndpointConstants.*;
import static com.eindopdracht_into_art.helpers.Validator.validateAndReturnErrorsIfAny;

@RestController
public class AuthenticationController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthenticationController(AuthenticationManager authManager,
                                    JwtService jwtService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    @PostMapping(EP_LOGIN)
    public ResponseEntity<Object> logIn(
            @Valid @RequestBody AuthRequestDto dto, BindingResult br
    ) {
        if (br.hasErrors()) {
            return validateAndReturnErrorsIfAny(br);
        }
        final var u = dto.getUsername();
        final var p = dto.getPassword();
        final var principalAndCredentials = new UsernamePasswordAuthenticationToken(u, p);

        AuthenticationResponse response;
        try {
            final var auth = authManager.authenticate(principalAndCredentials);
            final var userDetails = (UserDetails) auth.getPrincipal();
            final var jwt = jwtService.generateToken(userDetails);
            response = new AuthenticationResponse(jwt);

        } catch (AuthenticationException ex) {
            throw new BadCredentialsException("Foutief gebruikersnaam of wachtwoord", ex);
        } catch (IllegalArgumentException ex){
            throw new IllegalArgumentException("Onjuist gebruik van argumenten", ex);
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION,
                        PREFIX_BEARER + response.jwt())
                .body(response.getJwtMessage());
    }

    @GetMapping(EP_AUTHENTICATED)
    public ResponseEntity<String> helloUser(
    ){
        final var authenticated = SecurityContextHolder
                .getContext()
                .getAuthentication();

        return ResponseEntity.ok().body(authenticated.getPrincipal().toString());
    }

}