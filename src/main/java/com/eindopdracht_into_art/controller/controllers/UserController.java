package com.eindopdracht_into_art.controller.controllers;

import com.eindopdracht_into_art.controller.services.interfaces.UserService;
import com.eindopdracht_into_art.model.dtos.UserDto;
import com.eindopdracht_into_art.model.dtos.UserRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.eindopdracht_into_art.controller.helpers.Validator.validateAndReturnErrorsIfAny;


@RestController
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("user")
    public ResponseEntity<String> getUser(){
        return ResponseEntity.ok().body("user");
    }

    @PostMapping()
    public ResponseEntity<UserDto> register(
            @Valid @RequestBody UserRequestDto dto, BindingResult br
    ) {
        if (br.hasErrors()) {
            validateAndReturnErrorsIfAny(br);
        }
        final var user = userService.registerUser(dto);

        return ResponseEntity.ok().body(user);
    }

}
