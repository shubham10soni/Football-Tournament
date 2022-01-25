package com.Football.Tournament.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.Football.Tournament.dao.PlayerDao;
import com.Football.Tournament.entities.Players;

//THIS CLASS IS USED FOR IMPLEMENTING THE METHODS OF PLAYERS SERVICE INTERFACE.

@Component
public class PlayerServiceImpl implements PlayerService {
	
	

	@Autowired
	private PlayerDao playerdao;
	
	//IMPLEMENTATION OF METHODS

	@Override
	public Page<Players> listPlayers(Pageable pageRequest) {
	return playerdao.findAll(pageRequest);
	}

	@Override
	public Players createPlayer(Players player) {
	return playerdao.save(player);
	}

	@Override
	public Players findAPlayer(long id) {
	return playerdao.findById(id).get();
	}

	@Override
	public Players updatePlayer(long id, Players player) {
	Players ent=playerdao.findById(id).get();
	ent.setName(player.getName());
	ent.setAge(player.getAge());
	ent.setTeam_id(player.getTeam_id());
	return playerdao.save(ent);
	}

	@Override
	public void deletePlayer(long id) {
	Players ent=playerdao.findById(id).get();	
	playerdao.delete(ent);
	}

	@Override
	public List<Players> playersByTeamId(long team_id) {
		
	return playerdao.findByTeamId(team_id);
		
		
	}

	@Override
	public void deletePlayerByTeamId(long team_id) {
		List<Players> players=playerdao.findByTeamId(team_id);
		playerdao.deleteAll(players);
	}

	
	
	

}
