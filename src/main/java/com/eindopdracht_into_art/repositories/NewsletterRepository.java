package com.eindopdracht_into_art.repositories;

import com.eindopdracht_into_art.models.entities.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsletterRepository extends JpaRepository<Newsletter, Long> {

    Optional<Newsletter> findNewsletterByFileName(String filename);

}
