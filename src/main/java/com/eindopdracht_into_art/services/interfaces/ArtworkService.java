package com.eindopdracht_into_art.services.interfaces;

import com.eindopdracht_into_art.models.dtos.ArtworkCreationDto;
import com.eindopdracht_into_art.models.entities.Artwork;
import org.springframework.stereotype.Service;

@Service
public interface ArtworkService {

    void checkIfFilenameIsAlreadyTaken(String fileName);

    Artwork relayFromArtwork(ArtworkCreationDto dto);
}
