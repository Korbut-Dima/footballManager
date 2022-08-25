package com.footballManager.controllers;

import com.footballManager.configurations.PlayerTestConfiguration;
import com.footballManager.dto.PlayerCreateUpdateDto;
import com.footballManager.entities.Player;
import com.footballManager.entities.Team;
import com.footballManager.services.impl.PlayerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {PlayerController.class})
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = {PlayerTestConfiguration.class})
class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlayerServiceImpl playerService;

    private Player mockPlayer;
    private Team mockTeam;


    @BeforeEach
    void setUp(){
        mockTeam = Team.builder()
                .id(Long.valueOf(1))
                .name("Bukovina")
                .commissionForTransfer((float) 0.1)
                .balance(BigDecimal.valueOf(2000000))
                .build();

        mockPlayer = Player.builder().fullName("Xsavie")
                .dateOfBirth(Date.valueOf("1999-12-12"))
                .startOfCareer(Date.valueOf("2019-12-02"))
                .team(mockTeam).build();
    }

    @Test
    void findAll() throws Exception {
        given(playerService.findAll()).willReturn(List.of(mockPlayer));

        mockMvc.perform(get("/players/all"))
                .andExpect(status().isOk())
                .andExpect(content().string("["+mockPlayer.toJSON()+"]"));
    }

    @Test
    void createPlayer() throws Exception {
        given(playerService.createPlayer(any())).willReturn(mockPlayer);

        PlayerCreateUpdateDto playerCreateUpdateDto = PlayerCreateUpdateDto.builder()
                .fullName(mockPlayer.getFullName())
                .startOfCareer(mockPlayer.getStartOfCareer())
                .dateOfBirth(mockPlayer.getDateOfBirth())
                .team(mockPlayer.getTeam().getId()).build();

        mockMvc.perform(post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(playerCreateUpdateDto.toJSON()))
                .andExpect(status().isCreated())
                .andExpect(content().string(mockPlayer.toJSON()));
    }

    @Test
    void  updatePlayer() throws Exception {
        Player editedPlayer = Player.builder()
                .fullName("Anatoliy")
                .dateOfBirth(Date.valueOf("1918-04-04"))
                .startOfCareer(Date.valueOf("2020-02-02"))
                .team(mockTeam)
                .build();

        given(playerService.updatePlayer(any(),any())).willReturn(editedPlayer);

        PlayerCreateUpdateDto playerCreateUpdateDto = PlayerCreateUpdateDto.builder()
                .fullName("Anatoliy")
                .dateOfBirth(Date.valueOf("1918-04-04"))
                .startOfCareer(Date.valueOf("2020-02-02"))
                .team(Long.valueOf(1))
                .build();

        mockMvc.perform(put("/players/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(playerCreateUpdateDto.toJSON()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(editedPlayer.toJSON()));
    }

    @Test
    void deletePlayer() throws Exception {
        mockMvc.perform(delete("/players/1").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
    }

    @Test
    void checkValidation() throws Exception{
        PlayerCreateUpdateDto incorrectDto = PlayerCreateUpdateDto.builder()
                .fullName(mockPlayer.getFullName())
                .team(mockPlayer.getTeam().getId())
                .dateOfBirth(mockPlayer.getDateOfBirth())
                .startOfCareer(Date.valueOf("2023-12-12")) // Date in future
                .build();

        mockMvc.perform(post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(incorrectDto.toJSON()))
                .andExpect(status().is4xxClientError());
    }

}