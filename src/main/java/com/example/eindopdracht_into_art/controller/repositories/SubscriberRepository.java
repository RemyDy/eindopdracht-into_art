package com.example.eindopdracht_into_art.controller.repositories;

import com.example.eindopdracht_into_art.model.entities.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    Optional<Subscriber> findSubscriberByEmail(String email);

    Optional<Subscriber> findSubscriberByConfirmationToken(String token);
}


