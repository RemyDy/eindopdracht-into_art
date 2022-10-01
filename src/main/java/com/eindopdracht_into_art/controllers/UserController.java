package com.eindopdracht_into_art.controllers;

import com.eindopdracht_into_art.models.dtos.UserDto;
import com.eindopdracht_into_art.services.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.eindopdracht_into_art.controllers.endpoints.ControllerEndpointConstants.EP_USER;


@RestController
@RequestMapping(EP_USER)
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateUser(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserDto dto, BindingResult br
    ) {
        dto.setId(id);
        service.updateUser(dto);

        return ResponseEntity.ok().body(null); // change this
    }

}
