package com.eindopdracht_into_art.repositories;

import com.eindopdracht_into_art.models.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findUserByUsername(String username);

}
