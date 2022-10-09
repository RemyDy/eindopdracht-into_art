package com.eindopdracht_into_art.services;

import com.eindopdracht_into_art.models.dtos.NewsletterCreationDto;
import com.eindopdracht_into_art.models.dtos.ArtworkCreationDto;
import com.eindopdracht_into_art.models.entities.Artwork;
import com.eindopdracht_into_art.models.entities.Newsletter;
import com.eindopdracht_into_art.models.exceptions.RecordNotFoundException;
import com.eindopdracht_into_art.repositories.ArtistRepository;
import com.eindopdracht_into_art.services.interfaces.ArtistService;
import com.eindopdracht_into_art.services.interfaces.ArtworkService;
import com.eindopdracht_into_art.services.interfaces.NewsletterService;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final NewsletterService newsletterService;
    private final ArtworkService artworkService;

    public ArtistServiceImpl(ArtistRepository artistRepository, NewsletterService newsletterService, ArtworkService artworkService) {
        this.artistRepository = artistRepository;
        this.newsletterService = newsletterService;
        this.artworkService = artworkService;
    }

    @Override
    public void addNewsletter(NewsletterCreationDto dto, Long id) {
        final var artist = artistRepository.findArtistById(id)
                .orElseThrow(() -> new RecordNotFoundException("artist doesn't exist, newsletter cannot be created"));
        newsletterService.checkIfFilenameIsAlreadyTaken(dto.getFilename());
        artist.addNewsletter(new Newsletter("test", "test", "test"));
    }

    @Override
    public void addArtwork(ArtworkCreationDto dto, Long id) {
        final var artist = artistRepository.findArtistById(id)
                .orElseThrow(() -> new RecordNotFoundException("artist doesn't exist, artwork cannot be created"));

        artworkService.checkIfFilenameIsAlreadyTaken(dto.getFilename);
        artworkService.relayFromArtwork(dto);

        artist.addArtwork(new Artwork.Builder());
    }

}
