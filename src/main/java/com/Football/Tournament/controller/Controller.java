package com.Football.Tournament.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Football.Tournament.entities.Players;
import com.Football.Tournament.entities.Teams;
import com.Football.Tournament.response.ResponseHandler;
import com.Football.Tournament.services.PlayerService;
import com.Football.Tournament.services.TeamService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/home")
public class Controller {
	@Autowired
	private TeamService teamService;

	@Autowired
	private PlayerService playerService;

	

	// -------------------------------------------------

	// METHODS FOR "TEAMS" ENTITY

	// -------------------------------------------------

	@ApiOperation(value = "Get list of Teams.")
	@GetMapping("/listTeams")
	@ApiImplicitParams({ // to correct the swagger request url
			@ApiImplicitParam(name = "page_no", dataType = "int", paramType = "query", defaultValue = "0", value = "eg,enter 0 in the textfield,if you want to see first page"),
			@ApiImplicitParam(name = "size", dataType = "int", paramType = "query", defaultValue = "3", value = "Number of records per page") })

	public ResponseEntity<Object> listTeams(@PageableDefault(page = 0, size = 3) @ApiIgnore Pageable pageRequest) {
		try {
			Page<Teams> data = this.teamService.listTeams(pageRequest);
			if (data.isEmpty() == true)
				return ResponseHandler.generateResponse(HttpStatus.OK,
						"No value Present in Teams table or in this page!", data);
			else
				return ResponseHandler.generateResponse(HttpStatus.OK, "successfully retrived!", data);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, e.getMessage(), null);
		}
	}

	// THIS METHOD IS USED FOR CREATING A NEW TEAM.
	@ApiOperation(value = "Create a new team.")
	@PostMapping("/createTeam")
	public ResponseEntity<Object> createTeam(@RequestBody Teams team) {
		try {
			this.teamService.createTeam(team);
			return ResponseHandler.generateResponse(HttpStatus.OK, "successfully created!", team);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, e.getMessage(), null);
		}

	}

	// WITH THE HELP OF THIS METHOD YOU CAN FIND INFO. OF A PERTICULAR TEAM USING
	// ITS UNIQUE ID.
	@ApiOperation(value = "Find a team by its team_id.")
	@GetMapping("/findATeam/{id}")
	@ResponseBody
	public ResponseEntity<Object> findATeam(@PathVariable String id) {
		try {
			Teams data = this.teamService.findATeam(Long.parseLong(id));
			return ResponseHandler.generateResponse(HttpStatus.OK, "successfully retrived!", data);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, e.getMessage(), null);
		}
	}

	// THIS METHOD IS USED FOR UPDATING A TEAM USING ITS UNIQUE ID.
	@ApiOperation(value = "Update a Team using team_id.")
	@PutMapping("/updateTeam/{id}")
	public ResponseEntity<Object> updateTeam(@PathVariable String id, @RequestBody Teams team) {
		try {
			this.teamService.updateTeam(Long.parseLong(id), team);
			return ResponseHandler.generateResponse(HttpStatus.OK, "successfully updated!",null);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, e.getMessage(), null);
		}
	}

	// THIS METHOD IS USED FOR DELETING A TEAM USING ITS UNIQUE ID
	@ApiOperation(value = "Delete a Team using its team_id.")
	@DeleteMapping("/deleteTeam/{id}")
	public ResponseEntity<Object> deleteTeam(@PathVariable String id) {
		try {
			this.teamService.deleteTeam(Long.parseLong(id));
			return ResponseHandler.generateResponse(HttpStatus.OK, "successfully deleted!", null);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, e.getMessage(), null);
		}

	}

	// USING THE PLAYER ID OF THE PLAYER, THIS METHOD FINDS THE INFORMATION OF
	// PLAYER`S TEAM.
	@ApiOperation(value = "Find the team of a player using player id.")
	@GetMapping("/teamByPlayerId/{id}")
	public ResponseEntity<Object> teamByPlayerId(@PathVariable String id) {
		try {
			Teams data = this.teamService.teamByPlayerId(Long.parseLong(id));
			return ResponseHandler.generateResponse(HttpStatus.OK, "successfully retrived!", data);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, e.getMessage(), null);
		}
	}

	// ----------------------------------------------------------------------------------------------------------

	// METHODS FOR "PLAYERS" ENTITY

	// ----------------------------------------------------------------------------------------------------------

	// THIS METHOD RETURNS LIST OF PLAYERS WITH PAGINATION.
	@ApiOperation(value = "Get list of players.")
	@GetMapping("/listPlayers")
	@ApiImplicitParams({ // to correct the swagger request url
			@ApiImplicitParam(name = "page", dataType = "int", paramType = "query", defaultValue = "0", value = "eg,enter 0 in the textfield,if you want to see first page "),
			@ApiImplicitParam(name = "size", dataType = "int", paramType = "query", defaultValue = "3", value = "Number of records per page") })
	public ResponseEntity<Object> listPlayers(@PageableDefault(page = 0, size = 3) @ApiIgnore Pageable pageRequest) {
		try {

			Page<Players> data = this.playerService.listPlayers(pageRequest);
			if (data.isEmpty() == true)
				return ResponseHandler.generateResponse(HttpStatus.OK,
						"No value Present in players table or in this page!", data);
			else
				return ResponseHandler.generateResponse(HttpStatus.OK, "successfully retrived!", data);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, e.getMessage(), null);
		}

	}

	// THIS METHOD IS USED FOR CREATING A NEW PLAYER
	@ApiOperation(value = "Create a new player.")
	@PostMapping("/createPlayer")
	public ResponseEntity<Object> createPlayer(@RequestBody Players player) {
		try {
			this.playerService.createPlayer(player);
			return ResponseHandler.generateResponse(HttpStatus.OK, "successfully created!", player);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, e.getMessage(), null);
		}
	}

	// YOU CAN FIND A PLAYER WITH THE HELP OF ITS UNIQUE ID.
	@ApiOperation(value = "Find a player by its id.")
	@GetMapping("/findAPlayer/{id}")
	public ResponseEntity<Object> findAPlayer(@PathVariable String id) {
		try {
			Players data = this.playerService.findAPlayer(Long.parseLong(id));
			return ResponseHandler.generateResponse(HttpStatus.OK, "successfully retrived!", data);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, e.getMessage(), null);
		}
	}

	// THIS METHOD IS USED FOR UPDATING THE PLAYER INFO. USING ITS UNIQUE ID.
	@ApiOperation(value = "Update player info. using its id.")
	@PutMapping("/updatePlayer/{id}")
	public ResponseEntity<Object> updatePlayer(@PathVariable String id, @RequestBody Players player) {
		try {
			this.playerService.updatePlayer(Long.parseLong(id), player);
			return ResponseHandler.generateResponse(HttpStatus.OK, "successfully updated!",null);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, e.getMessage(), null);
		}
	}

	// THIS METHOD IS USED FOR DELETING THE PLAYER INFORMATION USING ITS UNIQUE ID.
	@ApiOperation(value = "Delete player info. using its id.")
	@DeleteMapping("/deletePlayer/{id}")
	public ResponseEntity<Object> deletePlayer(@PathVariable String id) {
		try {
			this.playerService.deletePlayer(Long.parseLong(id));
			return ResponseHandler.generateResponse(HttpStatus.OK, "successfully deleted!", null);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, e.getMessage(), null);
		}

	}

	// THIS METHOD GIVES US THE LIST OF ALL PLAYERS PLAYING FOR A PERTICULAR TEAM USING TEAM ID.
	@ApiOperation(value = "Find all the players playing in a perticular Team using team id.")
	@GetMapping("/playersByTeamId/{team_id}")
	public ResponseEntity<Object> playersByTeamId(@PathVariable String team_id) {
		try {
			List<Players> data = this.playerService.playersByTeamId(Long.parseLong(team_id));
			if (data.isEmpty() == true)
				return ResponseHandler.generateResponse(HttpStatus.OK, "There are no players in this team!", data);
			else
				return ResponseHandler.generateResponse(HttpStatus.OK, "successfully retrived!", data);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, e.getMessage(), null);
		}
	}
	
	// THIS METHOD WILL DELETE ALL THE PLAYERS PLAYING FOR A PERTICULAR TEAM USING TEAM ID.
		@ApiOperation(value = "Delete all the players playing in a perticular Team using team id.")
		@DeleteMapping("/deletePlayersByTeamId/{team_id}")
		public ResponseEntity<Object> deletePlayersByTeamId(@PathVariable String team_id) {
			try {
				this.playerService.deletePlayerByTeamId(Long.parseLong(team_id));
				return ResponseHandler.generateResponse(HttpStatus.OK, "Players who played for this team have been removed!", null);
			
			} catch (Exception e) {
				return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, e.getMessage(), null);
			}
		}

}