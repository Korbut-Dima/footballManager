package com.footballManager.services.interfaces;

import com.footballManager.dto.PlayerCreateUpdateDto;
import com.footballManager.entities.Player;

public interface PlayerService {
    Player createPlayer(PlayerCreateUpdateDto playerCreateUpdateDto);

    Iterable<Player> getAllPlayers();

    Player getPlayer(Long id);

    Player updatePlayer(Long id, PlayerCreateUpdateDto playerCreateUpdateDto);

    void deletePlayer(Long id);
}
