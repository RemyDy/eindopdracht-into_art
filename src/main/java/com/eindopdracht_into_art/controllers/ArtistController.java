package com.eindopdracht_into_art.controllers;

import com.eindopdracht_into_art.models.dtos.ArtworkCreationDto;
import com.eindopdracht_into_art.models.dtos.NewsletterCreationDto;
import com.eindopdracht_into_art.services.interfaces.ArtistService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.eindopdracht_into_art.helpers.Validator.validateAndReturnErrors;

@RestController("artist")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping("/{id}/newsletter")
    public ResponseEntity<String> createNewsletter(
            @PathVariable("id") Long id,
            @Valid @RequestBody NewsletterCreationDto dto, BindingResult br
    ) {
        if (br.hasErrors()) {
            validateAndReturnErrors(br);
        }
        artistService.addNewsletter(dto, id);
        return ResponseEntity.ok().body("nieuwsbrief succesvol aangemaakt");
    }

    @PostMapping("/{id}/artwork")
    public ResponseEntity<String> createArtWork(
            @PathVariable("id") Long id,
            @Valid @RequestBody ArtworkCreationDto dto, BindingResult br
    ) {
        if (br.hasErrors()) {
            validateAndReturnErrors(br);
        }
        artistService.addArtwork(dto, id);
        return ResponseEntity.ok().body("kunstwerk succesvol aangemaakt");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getSubscriberCount(
            @PathVariable("id") Long id
    ) {
        return null;
    }

}
