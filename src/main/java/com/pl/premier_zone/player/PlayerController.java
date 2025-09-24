package com.pl.premier_zone.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller: handles incoming HTTP requests, delegates to the service layer and returns the appropriate response
// RestController: marks the class as a Spring MVC controller where every method returns a domain object
// instead of a view
@RestController
@RequestMapping(path = "api/v1/player")
public class PlayerController {

    private final PlayerService playerService;

    // Autowired: allows controller to delegate business logic back to the service layer
    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // Handle all get requests
    // Params: Different request parameters so use RequestParam
    // getPlayers return a different player list based on the presence of these query parameters
    // if no parameters presented, return all players
    @GetMapping
    public List<Player> getPlayers(
            @RequestParam(required = false) String team,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String nation) {
        if (team != null && position != null) {
            return playerService.getPlayersByTeamAndPosition(team, position);
        } else if (team != null) {
            return playerService.getPlayersFromTeam(team);
        } else if (name != null) {
            return playerService.getPlayersByName(name);
        } else {
            return playerService.getPlayers(); // If no params are passed
        }
    }

    // Handles HTTP post requests to add players to DB
    // Calls our service class to add passed player request to our repo
    // returns the response entity for the created player
    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        Player createdPlayer = playerService.addPlayer(player);
        return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED); // Status 201 "CREATED"
    }

    // Handles HTTP put requests to update players in DB
    // Checks if player is in the DB, if yes then update if no then return status NOT FOUND
    @PutMapping
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
        Player resultPlayer = playerService.updatePlayer(player);
        if (resultPlayer != null) {
            return new ResponseEntity<>(resultPlayer, HttpStatus.OK); // Status "OK"
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Status "NOT FOUND"
        }
    }

    // HTTP Delete request specifically by their name
    // DeleteMapping: Since it is specific to NAME then put the name of the player in the brackets
    // PathVariable just takes the player name passed in the delete mapping
    @DeleteMapping("/{playerName}")
    public ResponseEntity<String> deletePlayer(@PathVariable String playerName) {
        playerService.deletePlayer(playerName);
        return new ResponseEntity<>("Player deleted successfully", HttpStatus.OK);
    }
}
