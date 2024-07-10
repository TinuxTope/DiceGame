package com.example.diceGameCristinaTomas.model.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "games")
public class Game {
    @Id
    private String id;
    private int playerId;
    private int die1;
    private int die2;
    private boolean win;

    public Game(int playerId, int die1, int die2) {
        this.playerId = playerId;
        this.die1 = die1;
        this.die2 = die2;
        this.win = (die1 + die2 == 7);
    }
    public Game(int playerId, int die1, int die2, boolean win) {
        this.playerId = playerId;
        this.die1 = die1;
        this.die2 = die2;
        this.win = win;
    }
}
