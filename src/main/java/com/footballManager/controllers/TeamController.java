package com.footballManager.controllers;

import com.footballManager.entities.Player;
import com.footballManager.entities.Team;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class TeamController {
    @GetMapping("/teams/{id}")
    public Set<Player> getPlayersByTeam(@PathVariable Long id){
      return null;
    }
}
