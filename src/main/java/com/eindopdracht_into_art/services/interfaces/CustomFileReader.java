package com.eindopdracht_into_art.services.interfaces;

import org.springframework.stereotype.Service;

@Service
public interface CustomFileReader {

    Object getFile(String subject, String fileName);
}
