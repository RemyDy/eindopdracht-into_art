package com.eindopdracht_into_art.repositories;

import com.eindopdracht_into_art.models.entities.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    Optional<Subscriber> findSubscriberByEmail(String email);

    Optional<Subscriber> findSubscriberByConfirmationToken(String token);
}


