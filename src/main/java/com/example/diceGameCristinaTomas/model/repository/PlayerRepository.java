package com.example.diceGameCristinaTomas.model.repository;

import com.example.diceGameCristinaTomas.model.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    Optional<Player> findByEmail(String email);
}
