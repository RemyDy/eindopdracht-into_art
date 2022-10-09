package com.eindopdracht_into_art.repositories;

import com.eindopdracht_into_art.models.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Optional<Artist> findArtistById(Long id);

}
