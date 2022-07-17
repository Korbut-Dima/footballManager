package com.footballManager.services.impl;

import com.footballManager.dto.PlayerCreateUpdateDto;
import com.footballManager.dto.TransferDto;
import com.footballManager.entities.Player;
import com.footballManager.entities.Team;
import com.footballManager.repositories.PlayerRepository;
import com.footballManager.repositories.TeamRepository;
import com.footballManager.services.interfaces.PlayerService;
import com.footballManager.utils.Calculation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository,TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public Iterable<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player createPlayer(PlayerCreateUpdateDto playerCreateUpdateDto) {
        Player player = Player.builder()
                .dateOfBirth(playerCreateUpdateDto.getDateOfBirth())
                .fullName(playerCreateUpdateDto.getFullName())
                .startOfCareer(playerCreateUpdateDto.getStartOfCareer())
                .team(teamRepository.findById(playerCreateUpdateDto.getTeam()).get())
                .build();
        return playerRepository.save(player);
    }

    @Override
    public Player getPlayer(Long id) {
        return playerRepository.findById(id).get();
    }

    @Override
    public Player updatePlayer(Long id, PlayerCreateUpdateDto playerCreateUpdateDto) {
        Player player = playerRepository.findById(id).get();
        BeanUtils.copyProperties(playerCreateUpdateDto, player);
        player.setTeam(teamRepository.findById(playerCreateUpdateDto.getTeam()).get());
        return playerRepository.save(player);
    }

    @Override
    public void deletePlayer(Long id) {
        Player player = playerRepository.findById(id).get();
        playerRepository.delete(player);
    }

    @Override
    public Player transferPlayer(TransferDto transferDto) {
        Player playerToTransfer = playerRepository.findByFullName(transferDto.getNameOfPlayer());
        Team teamBuyer = teamRepository.findByName(transferDto.getNameOfTeam());
        Team teamSeller = playerToTransfer.getTeam();

        BigDecimal valueOfTransfer = Calculation.getCostOfTransfer(
                teamSeller.getCommissionForTransfer(),
                playerToTransfer.getDateOfBirth(),
                playerToTransfer.getStartOfCareer()
        );

        teamBuyer.setBalance(teamBuyer.getBalance().subtract(valueOfTransfer));
        teamRepository.save(teamBuyer);

        teamSeller.setBalance(teamSeller.getBalance().add(valueOfTransfer));
        teamRepository.save(teamSeller);

        playerToTransfer.setTeam(teamBuyer);
        return playerRepository.save(playerToTransfer);
    }
}
