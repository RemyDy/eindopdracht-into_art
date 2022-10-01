package com.eindopdracht_into_art.controllers;

import com.eindopdracht_into_art.helpers.UriCreator;
import com.eindopdracht_into_art.models.dtos.RegistrationDto;
import com.eindopdracht_into_art.models.entities.Authority;
import com.eindopdracht_into_art.payload.RegistrationResponse;
import com.eindopdracht_into_art.services.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.eindopdracht_into_art.controllers.endpoints.ControllerEndpointConstants.EP_LOGIN;
import static com.eindopdracht_into_art.controllers.endpoints.ControllerEndpointConstants.EP_REGISTRATION;
import static com.eindopdracht_into_art.helpers.Validator.validateAndReturnErrorsIfAny;

@RestController
@RequestMapping(EP_REGISTRATION)
public class RegistrationController {

    UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<Object> register(
            @Valid @RequestBody RegistrationDto dto, BindingResult br
    ) {
        if (br.hasErrors()) {
            validateAndReturnErrorsIfAny(br);
        }
        final var user = userService.registerUser(dto);
        userService.addAuthority(user.getId(), Authority.Role.USER.toString());

        final var location = UriCreator.createUriById(EP_LOGIN, user.getId());
        final var payload = new RegistrationResponse(user.getUsername()).getResponse();

        return ResponseEntity.created(location).body(payload);
    }

}
