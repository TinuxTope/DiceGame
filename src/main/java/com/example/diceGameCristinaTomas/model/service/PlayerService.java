package com.example.diceGameCristinaTomas.model.service;

import com.example.diceGameCristinaTomas.model.domain.Player;
import com.example.diceGameCristinaTomas.model.domain.Role;
import com.example.diceGameCristinaTomas.model.repository.PlayerRepository;
import com.example.diceGameCristinaTomas.model.repository.GameRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

    @PostConstruct
    public void init() {
        if (playerRepository.count() == 0) {
            Player admin = new Player("admin@example.com", Role.ADMIN);
            admin.setPassword(new BCryptPasswordEncoder().encode("admin123"));
            playerRepository.save(admin);
        }
    }

    public Player updatePlayer(Integer id, String email) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new RuntimeException("Player not found"));
        player.setEmail(email);
        return playerRepository.save(player);
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(Integer id) {
        return playerRepository.findById(id).orElseThrow(() -> new RuntimeException("Player not found"));
    }

    public void deletePlayer(Integer id) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new RuntimeException("Player not found"));
        gameRepository.deleteByPlayerId(player.getId());
        playerRepository.delete(player);
    }

    public void setPassword(Integer id, String password) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new RuntimeException("Player not found"));
        player.setPassword(new BCryptPasswordEncoder().encode(password));
        playerRepository.save(player);
    }

    public Integer getId(String email) {
        Player player = playerRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Player not found"));
        return player.getId();
    }

    public String getEmail(Integer id) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new RuntimeException("Player not found"));
        return player.getEmail();
    }
}
