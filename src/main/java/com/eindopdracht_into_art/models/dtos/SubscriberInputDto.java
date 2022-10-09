package com.eindopdracht_into_art.models.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class SubscriberInputDto {

    @Length(min = 2, max = 64,
            message = "Invoer naam is ongeldig, minimaal 2 en maximaal 64 karakters invullen.")
    private String name = "abonnee";

    @NotBlank(message = "E-mail is verplicht, veld mag niet leeg zijn")
    @Email(message = "Invoer e-mail is ongeldig")
    private String email;

    @NotBlank
    private String newsletter;

    private String confirmationToken;

    private LocalDateTime tokenCreatedAt;

    private LocalDateTime tokenExpiredAt;

    private LocalDateTime tokenConfirmedAt;

    //region Getters

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public LocalDateTime getTokenCreatedAt() {
        return tokenCreatedAt;
    }

    public LocalDateTime getTokenExpiredAt() {
        return tokenExpiredAt;
    }

    public LocalDateTime getTokenConfirmedAt() {
        return tokenConfirmedAt;
    }

    public String getNewsletter() {
        return newsletter;
    }

    //endregion

    //region Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public void setTokenCreatedAt(LocalDateTime tokenCreatedAt) {
        this.tokenCreatedAt = tokenCreatedAt;
    }

    public void setTokenExpiredAt(LocalDateTime tokenExpiredAt) {
        this.tokenExpiredAt = tokenExpiredAt;
    }

    public void setTokenConfirmedAt(LocalDateTime tokenConfirmedAt) {
        this.tokenConfirmedAt = tokenConfirmedAt;
    }

    public void setNewsletter(String newsletter) {
        this.newsletter = newsletter;
    }

    //endregion
}
