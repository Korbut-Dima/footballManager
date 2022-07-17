package com.footballManager.repositories;

import com.footballManager.entities.Player;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player,Long> {

    Player findByFullName(String nameOfPlayer);
}
