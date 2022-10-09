package com.eindopdracht_into_art.controllers;

import com.eindopdracht_into_art.models.dtos.UserAccountDto;
import com.eindopdracht_into_art.payload.UserAccountResponse;
import com.eindopdracht_into_art.services.interfaces.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.eindopdracht_into_art.controllers.endpoints.ControllerEndpoint.EP_USER;
import static com.eindopdracht_into_art.helpers.Validator.validateAndReturnErrors;


@RestController
@RequestMapping(EP_USER)
public class UserAccountController {

    private final UserAccountService service;

    public UserAccountController(UserAccountService service) {
        this.service = service;
    }

    @GetMapping("profile")
    public ResponseEntity<Long> getUser(
            @Valid @RequestBody UserAccountDto dto, BindingResult br
    ) {
        if (br.hasErrors()) {
            validateAndReturnErrors(br);
        }
        UserAccountResponse payload = new UserAccountResponse(service.getUserByUsername(dto));
        return ResponseEntity.ok().body(payload.getUserId());
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateUser(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserAccountDto dto, BindingResult br
    ) {
        dto.setId(id);
        service.updateUserAccount(dto);

        return ResponseEntity.ok().body(null); // change this
    }

}
