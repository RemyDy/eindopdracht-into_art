package com.eindopdracht_into_art.services;

import com.eindopdracht_into_art.models.exceptions.RecordExistsException;
import com.eindopdracht_into_art.repositories.NewsletterRepository;
import com.eindopdracht_into_art.services.interfaces.NewsletterService;
import org.springframework.stereotype.Service;

@Service
public class NewsletterServiceImpl implements NewsletterService {

    private NewsletterRepository newsletterRepository;

    public NewsletterServiceImpl(NewsletterRepository newsletterRepository) {
        this.newsletterRepository = newsletterRepository;
    }

    @Override
    public void checkIfFilenameIsAlreadyTaken(String fileName) {
        final var recordExists = newsletterRepository.findNewsletterByFileName(fileName)
                .isPresent();

        if (recordExists) {
            throw new RecordExistsException();
        }
    }


}
