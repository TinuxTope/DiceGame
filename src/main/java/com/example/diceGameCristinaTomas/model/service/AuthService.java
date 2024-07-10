package com.example.diceGameCristinaTomas.model.service;

import com.example.diceGameCristinaTomas.model.domain.Player;
import com.example.diceGameCristinaTomas.model.domain.Role;
import com.example.diceGameCristinaTomas.model.dto.AuthRequest;
import com.example.diceGameCristinaTomas.model.dto.AuthResponse;
import com.example.diceGameCristinaTomas.model.repository.PlayerRepository;
import com.example.diceGameCristinaTomas.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;

    public Player register(AuthRequest request) {
        Optional<Player> existingPlayer = playerRepository.findByEmail(request.getEmail());
        if (existingPlayer.isPresent()) {
            throw new RuntimeException("Player with email already exists");
        }
        Player player = new Player(request.getEmail(), Role.USER);
        player.setPassword(passwordEncoder.encode(request.getPassword()));
        return playerRepository.save(player);
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        final String jwt = jwtService.generateToken(userDetails.getUsername());
        return new AuthResponse(jwt);
    }
}
