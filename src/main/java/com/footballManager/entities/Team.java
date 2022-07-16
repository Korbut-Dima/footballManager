package com.footballManager.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Float commissionForTransfer;
    private BigDecimal balance;

    @JsonBackReference
    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Player> players;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name1) {
        name = name1;
    }

    public Float getCommissionForTransfer() {
        return commissionForTransfer;
    }

    public void setCommissionForTransfer(Float commissionForTransfer) {
        this.commissionForTransfer = commissionForTransfer;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
