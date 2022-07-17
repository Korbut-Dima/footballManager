package com.footballManager.controllers;

import com.footballManager.dto.PlayerCreateUpdateDto;
import com.footballManager.dto.TransferDto;
import com.footballManager.entities.Player;
import com.footballManager.services.interfaces.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("players")
public class PlayerController {

    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Player createPlayer(@RequestBody @Valid PlayerCreateUpdateDto playerCreateUpdateDto){
        return playerService.createPlayer(playerCreateUpdateDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Iterable<Player> getAllPlayers(){
        return playerService.getAllPlayers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Player getPlayer (@PathVariable("id") Long id){
        return playerService.getPlayer(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable("id") Long id,
                               @RequestBody @Valid  PlayerCreateUpdateDto playerCreateUpdateDto){
        return playerService.updatePlayer(id, playerCreateUpdateDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable("id") Long id){
        playerService.deletePlayer(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/transfer")
    public Player transferPlayer(@RequestBody @Valid  TransferDto transferDto){
        return playerService.transferPlayer(transferDto);
    }



}
