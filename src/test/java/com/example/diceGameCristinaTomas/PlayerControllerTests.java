package com.example.diceGameCristinaTomas;

import com.example.diceGameCristinaTomas.controller.PlayerController;
import com.example.diceGameCristinaTomas.model.domain.Player;
import com.example.diceGameCristinaTomas.model.domain.Role;
import com.example.diceGameCristinaTomas.model.service.PlayerService;
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
public class PlayerControllerTests {
    @MockBean
    private PlayerService playerService;

    @Autowired
    private PlayerController playerController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(playerController).build();
    }

    @Test
    void getAllPlayersTest() throws Exception {
        Player player = new Player("TestPlayer", Role.USER);
        when(playerService.getAllPlayers()).thenReturn(Collections.singletonList(player));

        mockMvc.perform(get("/players")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("TestPlayer"))
                .andExpect(jsonPath("$[0].role").value("USER"));
    }
}
