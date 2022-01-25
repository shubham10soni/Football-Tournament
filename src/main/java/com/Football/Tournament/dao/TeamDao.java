package com.Football.Tournament.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Football.Tournament.entities.Teams;

//THIS IS A DAO INTERFACE EXTENDING JPA REPOSITORY FOR TEAMS ENTITY TO PERFORM CRUD OPERATIONS.

@Repository
public interface TeamDao extends JpaRepository<Teams, Long> {
	
}
