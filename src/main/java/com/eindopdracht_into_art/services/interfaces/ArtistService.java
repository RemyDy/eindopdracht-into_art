package com.eindopdracht_into_art.services.interfaces;

import com.eindopdracht_into_art.models.dtos.NewsletterCreationDto;
import com.eindopdracht_into_art.models.dtos.ArtworkCreationDto;
import org.springframework.stereotype.Service;

@Service
public interface ArtistService {


    void addNewsletter(NewsletterCreationDto dto, Long id);

    void addArtwork(ArtworkCreationDto dto, Long id);
}
