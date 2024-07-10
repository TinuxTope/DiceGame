package com.example.diceGameCristinaTomas.controller;

import com.example.diceGameCristinaTomas.model.domain.Player;
import com.example.diceGameCristinaTomas.model.dto.AuthRequest;
import com.example.diceGameCristinaTomas.model.dto.AuthResponse;
import com.example.diceGameCristinaTomas.model.service.AuthService;
import com.example.diceGameCristinaTomas.security.jwt.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @Operation(summary = "Registers a new player")
    @ApiResponse(responseCode = "200", description = "Player registered successfully",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Player.class)) })
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        Player player = authService.register(request);
        return ResponseEntity.ok(player);
    }

    @Operation(summary = "Authenticates a player and returns a JWT token")
    @ApiResponse(responseCode = "200", description = "Authenticated successfully",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class)) })
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Logs out a player")
    @ApiResponse(responseCode = "200", description = "Logged out successfully")
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("Logged out successfully");
    }
}
