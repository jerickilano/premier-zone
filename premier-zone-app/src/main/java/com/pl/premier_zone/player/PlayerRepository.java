package com.pl.premier_zone.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Extends Jpa repo interface which provides CRUD operations for the player entity
// Player - specifies entity type
// String - Player's primary key
@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {

    void deleteByName(String playerName); // Delete players by name
    Optional<Player> findByName(String name); // Optional just in case player not found in repo
}
