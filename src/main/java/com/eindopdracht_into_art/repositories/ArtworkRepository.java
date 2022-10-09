package com.eindopdracht_into_art.repositories;

import com.eindopdracht_into_art.models.entities.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtworkRepository extends JpaRepository<Artwork, Long> {

    Optional<Artwork> findArtworkByTitle(String title);

}
