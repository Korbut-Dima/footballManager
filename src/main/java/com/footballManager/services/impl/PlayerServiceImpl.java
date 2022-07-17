package com.footballManager.services.impl;

import com.footballManager.dto.PlayerCreateUpdateDto;
import com.footballManager.dto.TeamCreateUpdateDto;
import com.footballManager.dto.TransferDto;
import com.footballManager.entities.Player;
import com.footballManager.entities.Team;
import com.footballManager.exceptions.ApiValidationException;
import com.footballManager.exceptions.EntityNotFoundException;
import com.footballManager.repositories.PlayerRepository;
import com.footballManager.repositories.TeamRepository;
import com.footballManager.services.interfaces.PlayerService;
import com.footballManager.services.interfaces.TeamService;
import com.footballManager.utils.Calculation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamService teamService;

    public PlayerServiceImpl(PlayerRepository playerRepository,TeamService teamService) {
        this.playerRepository = playerRepository;
        this.teamService = teamService;
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
                .team(teamService.getTeam(playerCreateUpdateDto.getTeam()))
                .build();
        return playerRepository.save(player);
    }

    @Override
    public Player getPlayer(Long id) {
        return playerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public Player updatePlayer(Long id, PlayerCreateUpdateDto playerCreateUpdateDto) {
        Player player = playerRepository.findById(id).get();
        BeanUtils.copyProperties(playerCreateUpdateDto, player);
        player.setTeam(teamService.getTeam(playerCreateUpdateDto.getTeam()));
        return playerRepository.save(player);
    }

    @Override
    public void deletePlayer(Long id) {
        Player player = playerRepository.findById(id).get();
        playerRepository.delete(player);
    }

    @Override
    public Player transferPlayer(TransferDto transferDto) {
        Player playerToTransfer = playerRepository.findById(transferDto.getIdOfPlayer())
                .orElseThrow(()-> new EntityNotFoundException(transferDto.getIdOfPlayer()));

        if(playerToTransfer.getTeam() != teamService.getTeam(transferDto.getIdOfTeam())){

        Team teamBuyer = teamService.getTeam(transferDto.getIdOfTeam());
        Team teamSeller = playerToTransfer.getTeam();

        BigDecimal valueOfTransfer = Calculation.getCostOfTransfer(
                teamSeller.getCommissionForTransfer(),
                playerToTransfer.getDateOfBirth(),
                playerToTransfer.getStartOfCareer()
        );

        teamBuyer.setBalance(teamBuyer.getBalance().subtract(valueOfTransfer));
        TeamCreateUpdateDto buyerTeamCreateUpdateDto = new TeamCreateUpdateDto();
        BeanUtils.copyProperties(teamBuyer, buyerTeamCreateUpdateDto);
        teamService.updateTeam(buyerTeamCreateUpdateDto, teamBuyer.getId());


        teamSeller.setBalance(teamSeller.getBalance().add(valueOfTransfer));
        TeamCreateUpdateDto sellerTeamCreateUpdateDto = new TeamCreateUpdateDto();
        BeanUtils.copyProperties(teamSeller, sellerTeamCreateUpdateDto);
        teamService.updateTeam(sellerTeamCreateUpdateDto, teamSeller.getId());

        playerToTransfer.setTeam(teamBuyer);
        return playerRepository.save(playerToTransfer);

        }
        else {
            throw new ApiValidationException("Player is already in that team");
        }
    }
}
