package com.example.diceGameCristinaTomas;

import com.example.diceGameCristinaTomas.controller.GameController;
import com.example.diceGameCristinaTomas.model.domain.Game;
import com.example.diceGameCristinaTomas.model.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class GameControllerTests {
    @MockBean
    private GameService gameService;

    @Autowired
    private GameController gameController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
    }

    @Test
    void getGamesByPlayerIdTest() throws Exception {
        Game game = new Game(1, 3, 4, true);
        when(gameService.getGamesByPlayerId(1)).thenReturn(Collections.singletonList(game));

        mockMvc.perform(get("/players/1/games")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].die1").value(3))
                .andExpect(jsonPath("$[0].die2").value(4))
                .andExpect(jsonPath("$[0].win").value(true));
    }
}
