package com.eindopdracht_into_art.helpers;

import com.eindopdracht_into_art.models.exceptions.RecordNotFoundException;

import java.io.IOException;
import java.nio.file.Paths;

public record CustomFileReader(String subject, String fileName) {

    public void checkUploadPath() {
        final String uploadParent = "C:\\Users\\Public\\Documents\\into_art\\upload\\";
        final var uploadChild = "%s\\%s".formatted(subject, fileName);
        final var pathUpload = Paths.get(uploadParent).resolve(uploadChild);

        try {
            pathUpload.toRealPath(); // check if path really exists
        } catch (IOException e) {
            throw new RecordNotFoundException("File is niet gevonden, deze eerst toevoegen s.v.p.");
        }
    }

    public void uploadFile() {
        // logic
    }

}
