package com.eindopdracht_into_art.controllers;

import com.eindopdracht_into_art.services.interfaces.NewsletterService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsletterController {

    NewsletterService newsletterService;

    public NewsletterController(NewsletterService newsletterService) {
        this.newsletterService = newsletterService;
    }

}
