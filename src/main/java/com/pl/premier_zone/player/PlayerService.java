package com.pl.premier_zone.player;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Service Layer contains the business logic and sits between the controller and repository to interact with the DB
// This handles business logic for managing players
// Leverage player repository to perform CRUD operations on the player entity

// Component: annotation to mark java class as a component
// This tells spring that his class should be managed by the spring container
// This means that spring will create an instance of this class and manage its lifecycle
@Component
public class PlayerService {
    private final PlayerRepository playerRepository; // Field to store repository

    // Autowired: to inject this player repo bean into the service which will enable it to interact with the DB
    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // Methods for Business logic

    // Returns a list of every single player
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    // Get players from a specific team
    public List<Player> getPlayersFromTeam(String teamName) {
        return playerRepository.findAll().stream().filter(player -> teamName.equals(player.getTeam()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByTeamAndPosition(String team, String position) {
        return playerRepository.findAll().stream()
                .filter(player -> team.equals(player.getTeam()) && position.equals(player.getPos()))
                .collect(Collectors.toList());
    }

    // Get all players with name
    public List<Player> getPlayersByName (String searchText) {
        return playerRepository.findAll().stream().filter(player -> player.getName().toLowerCase()
                .contains(searchText.toLowerCase())).collect(Collectors.toList());
    }

    // Add player to REPO using .save()
    public Player addPlayer(Player player) {
        playerRepository.save(player);
        return player;
    }

    // Update existing players info (player moves teams)
    public Player updatePlayer (Player updatedPlayer) {
        Optional<Player> existingPlayer = playerRepository.findByName(updatedPlayer.getName()); // get existing player

        if (existingPlayer.isPresent()) {
            Player playerToUpdate = existingPlayer.get(); // initialize player to update to make edits
            
            // Update all fields
            playerToUpdate.setName(updatedPlayer.getName());
            playerToUpdate.setTeam(updatedPlayer.getTeam());
            playerToUpdate.setPos(updatedPlayer.getPos());
            playerToUpdate.setNation(updatedPlayer.getNation());
            playerToUpdate.setAge(updatedPlayer.getAge());
            playerToUpdate.setMp(updatedPlayer.getMp());
            playerToUpdate.setStarts(updatedPlayer.getStarts());
            playerToUpdate.setMin(updatedPlayer.getMin());
            playerToUpdate.setGls(updatedPlayer.getGls());
            playerToUpdate.setAst(updatedPlayer.getAst());
            playerToUpdate.setPk(updatedPlayer.getPk());
            playerToUpdate.setCrdy(updatedPlayer.getCrdy());
            playerToUpdate.setCrdr(updatedPlayer.getCrdr());
            playerToUpdate.setXg(updatedPlayer.getXg());
            playerToUpdate.setXag(updatedPlayer.getXag());

            playerRepository.save(playerToUpdate); // save new player to repository
            return playerToUpdate; // Optional- used to see if player was correctly updated
        }
        return null; // If nothing was found in the DB, IF PLAYER IS NOT PRESENT IN THE DB
    }

    // Delete players from repo
    // Transactional: ensures that the operations is part of a transation
    // meaning it will maintain the data integrity during the delete operation
    @Transactional
    public void deletePlayer(String playerName) {
        playerRepository.deleteByName(playerName);
    }
}
