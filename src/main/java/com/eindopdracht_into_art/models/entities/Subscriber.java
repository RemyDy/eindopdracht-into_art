package com.eindopdracht_into_art.models.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "subscriber")
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String confirmationToken;

    @Column(nullable = false)
    private LocalDateTime tokenCreatedAt;

    @Column(nullable = false)
    private LocalDateTime tokenExpiredAt;

    private LocalDateTime tokenConfirmedAt = null;

    private String name;

    protected Subscriber() {
    } // JPA vereist een public of protected no-args constructor

    Subscriber(Builder builder) {
//        this.id = builder.id;
        this.email = builder.email;
        this.confirmationToken = builder.confirmationToken;
        this.tokenCreatedAt = builder.tokenCreatedAt;
        this.tokenExpiredAt = builder.tokenExpiredAt;
        this.name = builder.name;
    }

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

    public LocalDateTime getTokenExpiredAt() {
        return tokenExpiredAt;
    }

    public LocalDateTime getTokenCreatedAt() {
        return tokenCreatedAt;
    }

    public LocalDateTime getTokenConfirmedAt() {
        return tokenConfirmedAt;
    }
    //endregion

    //region Setter
    public void setTokenConfirmedAt(LocalDateTime tokenConfirmedAt) {
        this.tokenConfirmedAt = tokenConfirmedAt;
    }
    //endregion

    //region Builder
    public static class Builder {
//        private Long id;
        private final String email;
        private final String confirmationToken;
        private final LocalDateTime tokenCreatedAt;
        private final LocalDateTime tokenExpiredAt;
        private String name;

        public Builder(
                String email,
                String confirmationToken,
                LocalDateTime tokenCreatedAt,
                LocalDateTime tokenExpiredAt
        ) {
            this.email = email;
            this.confirmationToken = confirmationToken;
            this.tokenCreatedAt = tokenCreatedAt;
            this.tokenExpiredAt = tokenExpiredAt;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Subscriber build() {
            return new Subscriber(this);
        }
    }
    //endregion
}



