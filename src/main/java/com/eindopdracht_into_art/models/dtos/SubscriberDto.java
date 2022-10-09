package com.eindopdracht_into_art.models.dtos;

import java.time.LocalDateTime;

public class SubscriberDto {
    private Long id;
    private String name;
    private String email;
    private String confirmationToken;
    private LocalDateTime tokenCreatedAt;
    private LocalDateTime tokenExpiredAt;
    private LocalDateTime tokenConfirmedAt;
    private String newsletter;

    //region Getters
    public Long getId() {
        return id;
    }

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
    public void setId(Long id) {
        this.id = id;
    }

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
