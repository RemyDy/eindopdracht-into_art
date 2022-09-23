package com.eindopdracht_into_art.controller.controllers;

import com.eindopdracht_into_art.controller.services.JwtService;
import com.eindopdracht_into_art.model.dtos.AuthRequestDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.eindopdracht_into_art.controller.helpers.Validator.validateAndReturnErrorsIfAny;
import static com.eindopdracht_into_art.controller.helpers.GlobalConstant.*;

@RestController
@RequestMapping()
public class AuthController {

    private static final String LOGIN = "/login";

    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authManager,
                          JwtService jwtService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    @PostMapping(LOGIN)
    public ResponseEntity<Object> logIn(
            @Valid @RequestBody AuthRequestDto dto, BindingResult br
    ) {
        if (br.hasErrors()) {
            return validateAndReturnErrorsIfAny(br);
        }
        final var principalAndCredentials = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        final var auth = authManager.authenticate(principalAndCredentials);
        final var principal = (UserDetails) auth.getPrincipal();
        final var username = jwtService.extractUsername(principal.toString());
        final var jwt = jwtService.generateToken(dto);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, BEARER + jwt)
                .body("%s autorisatie is succesvol. %s".formatted(username, jwt));
    }

}
