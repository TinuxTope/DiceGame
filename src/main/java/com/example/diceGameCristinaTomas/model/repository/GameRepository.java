package com.example.diceGameCristinaTomas.model.repository;

import com.example.diceGameCristinaTomas.model.domain.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GameRepository extends MongoRepository<Game, String> {
    List<Game> findByPlayerId(int playerId);
    void deleteByPlayerId(int playerId);
}
