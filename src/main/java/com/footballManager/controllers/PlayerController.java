package com.footballManager.controllers;

import com.footballManager.dto.PlayerCreateUpdateDto;
import com.footballManager.entities.Player;
import com.footballManager.services.interfaces.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("players")
public class PlayerController {

   private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public Player createPlayer(@RequestBody PlayerCreateUpdateDto playerCreateUpdateDto){
        return playerService.createPlayer(playerCreateUpdateDto);
    }

    @GetMapping
    public Iterable<Player> getAllPlayers(){
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public Player getPlayer (@PathVariable("id") Long id){
        return playerService.getPlayer(id);
    }

    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable("id") Long id, @RequestBody PlayerCreateUpdateDto playerCreateUpdateDto){
        return playerService.updatePlayer(id, playerCreateUpdateDto);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable("id") Long id){
        playerService.deletePlayer(id);
    }

    @PostMapping("/transfer/{id}")
    public Player transferPlayer(){
        return null;
    }



}
