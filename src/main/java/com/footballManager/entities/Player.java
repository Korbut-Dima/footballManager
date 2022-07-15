package com.footballManager.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name="players")
public class Player {
    public Player() {
    }

    public Player(Long id, String fullName, Date dateOfBirth, Date startOfCareer, Team team) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.startOfCareer = startOfCareer;
        this.team = team;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private Date dateOfBirth;
    private Date startOfCareer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Team team;

}
