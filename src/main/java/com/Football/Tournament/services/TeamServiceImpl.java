package com.Football.Tournament.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.Football.Tournament.dao.PlayerDao;
import com.Football.Tournament.dao.TeamDao;
import com.Football.Tournament.entities.Players;
import com.Football.Tournament.entities.Teams;

//THIS CLASS IS USED FOR IMPLEMENTING THE METHODS OF TEAMS SERVICE INTERFACE.
@Component
public class TeamServiceImpl implements TeamService {
	
	@Autowired
	private TeamDao teamdao;
	
	@Autowired
	private PlayerDao playerdao;
	
	//IMPLEMENTATION OF METHODS

	@Override
	public Page<Teams> listTeams(Pageable pageRequest) {
	return teamdao.findAll(pageRequest);
	}

	@Override
	public Teams createTeam(Teams team) {
    return teamdao.save(team);
		
	}

	@Override
	public Teams findATeam(long id) {
	return teamdao.findById(id).get();
	}

	@Override
	public Teams updateTeam(long id,Teams team) {
	Teams ent=teamdao.findById(id).get();
	ent.setName(team.getName());
	ent.setLocation(team.getLocation());
	return teamdao.save(ent);
	}

	@Override
	public void deleteTeam(long id) {
	Teams ent=teamdao.findById(id).get();	
	teamdao.delete(ent);
	}

	@Override
	public Teams teamByPlayerId(long id) {
		Players ent=playerdao.findById(id).get();
		return teamdao.findById(ent.getTeam_id()).get();
		
		
	}

	

}
