package com.example.diceGameCristinaTomas.controller;

import com.example.diceGameCristinaTomas.model.domain.Game;
import com.example.diceGameCristinaTomas.model.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players/{playerId}/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping
    public Game createGame(@PathVariable int playerId, @RequestParam int die1, @RequestParam int die2) {
        return gameService.createGame(playerId, die1, die2);
    }

    @GetMapping
    public List<Game> getGamesByPlayerId(@PathVariable int playerId) {
        return gameService.getGamesByPlayerId(playerId);
    }

    @DeleteMapping
    public void deleteGamesByPlayerId(@PathVariable int playerId) {
        gameService.deleteGamesByPlayerId(playerId);
    }
}
