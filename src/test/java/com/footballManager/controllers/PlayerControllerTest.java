package com.footballManager.controllers;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PlayerController.class)
class PlayerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getAllPlayers() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/all");
        MvcResult result = mvc.perform(request).andReturn();
    }
    @Disabled
    @Test
    void findAllByPage() {
    }
    @Disabled
    @Test
    void createPlayer() {
    }
    @Disabled
    @Test
    void getPlayer() {
    }
    @Disabled
    @Test
    void updatePlayer() {
    }
    @Disabled
    @Test
    void deletePlayer() {
    }
    @Disabled
    @Test
    void transferPlayer() {
    }
}