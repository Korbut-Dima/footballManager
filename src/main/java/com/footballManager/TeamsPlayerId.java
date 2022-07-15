package com.footballManager;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TeamsPlayerId implements Serializable {
    private static final long serialVersionUID = -4537635636735039306L;
    @Column(name = "team_id", nullable = false)
    private Long teamId;

    @Column(name = "players_id", nullable = false)
    private Long playersId;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getPlayersId() {
        return playersId;
    }

    public void setPlayersId(Long playersId) {
        this.playersId = playersId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TeamsPlayerId entity = (TeamsPlayerId) o;
        return Objects.equals(this.playersId, entity.playersId) &&
                Objects.equals(this.teamId, entity.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playersId, teamId);
    }

}