package com.example.diceGameCristinaTomas.model.service;

import com.example.diceGameCristinaTomas.model.domain.Game;
import com.example.diceGameCristinaTomas.model.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public Game createGame(Integer playerId, int die1, int die2) {
        return gameRepository.save(new Game(playerId, die1, die2));
    }

    public List<Game> getGamesByPlayerId(Integer playerId) {
        return gameRepository.findByPlayerId(playerId);
    }

    public void deleteGamesByPlayerId(Integer playerId) {
        gameRepository.deleteByPlayerId(playerId);
    }
}
