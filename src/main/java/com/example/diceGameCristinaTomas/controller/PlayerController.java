package com.example.diceGameCristinaTomas.controller;

import com.example.diceGameCristinaTomas.model.domain.Player;
import com.example.diceGameCristinaTomas.model.domain.Role;
import com.example.diceGameCristinaTomas.model.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable Integer id, @RequestParam String email) {
        return playerService.updatePlayer(id, email);
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable Integer id) {
        return playerService.getPlayerById(id);
    }

    @DeleteMapping("/{id}")
    public String deletePlayer(@PathVariable Integer id, @RequestParam Role role) {
        if (role != Role.ADMIN) {
            throw new RuntimeException("Only ADMIN can delete players.");
        }
        playerService.deletePlayer(id);
        return "Player deleted successfully";
    }
}
