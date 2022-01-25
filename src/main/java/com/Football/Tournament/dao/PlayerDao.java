package com.Football.Tournament.dao;

//THIS IS A DAO INTERFACE EXTENDING JPA REPOSITORY FOR PLAYER ENTITY TO PERFORM CRUD OPERATIONS. 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Football.Tournament.entities.Players;

@Repository
public interface PlayerDao extends JpaRepository<Players, Long> {

// USING @QUERY ANNOTATION IN SPRING DATA JPA TO EXECUTE JPQL QUERIES.
	//THIS WILL SELECT PLAYERS WHICH BELONG TO PERTICULAR TEAM_ID.
	
@Query("Select p From Players p WHERE p.team_id = :team_id")	
List<Players> findByTeamId(@Param("team_id") long team_id);
}
