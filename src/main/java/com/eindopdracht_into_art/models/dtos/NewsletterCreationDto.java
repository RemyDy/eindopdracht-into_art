package com.eindopdracht_into_art.models.dtos;

import javax.validation.constraints.NotBlank;

public class NewsletterCreationDto {
    @NotBlank
    private String filename;

    @NotBlank
    private String filepath;

    @NotBlank
    public String subscription;

    //region Getters
    public String getFilename() {
        return filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public String getSubscription() {
        return subscription;
    }

    //endregion

    //region Setters
    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    //endregion
}
