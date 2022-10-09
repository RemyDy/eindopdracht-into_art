package com.eindopdracht_into_art.services;

import com.eindopdracht_into_art.models.dtos.ArtworkCreationDto;
import com.eindopdracht_into_art.models.entities.Artwork;
import com.eindopdracht_into_art.models.exceptions.RecordExistsException;
import com.eindopdracht_into_art.repositories.ArtworkRepository;
import com.eindopdracht_into_art.services.interfaces.ArtworkService;
import org.springframework.stereotype.Service;

@Service
public class ArtworkServiceImpl implements ArtworkService {

    private final ArtworkRepository artworkRepository;

    public ArtworkServiceImpl(ArtworkRepository artworkRepository) {
        this.artworkRepository = artworkRepository;
    }

    @Override
    public void checkIfFilenameIsAlreadyTaken(String fileName) {
        final var recordExists = artworkRepository.findArtworkByTitle(fileName).isPresent();

        if(recordExists){
            throw new RecordExistsException();
        }
    }

    @Override
    public Artwork relayFromArtwork(ArtworkCreationDto dto) {
        final var artwork = new Artwork.Builder();
        return null;
    }


}
