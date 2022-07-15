package com.footballManager;

import com.footballManager.entities.Player;
import com.footballManager.entities.Team;

import javax.persistence.*;

@Entity
@Table(name = "teams_players")
public class TeamsPlayer {
    @EmbeddedId
    private TeamsPlayerId id;

    @MapsId("teamId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @MapsId("playersId")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "players_id", nullable = false)
    private Player players;

    public TeamsPlayerId getId() {
        return id;
    }

    public void setId(TeamsPlayerId id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Player getPlayers() {
        return players;
    }

    public void setPlayers(Player players) {
        this.players = players;
    }

}