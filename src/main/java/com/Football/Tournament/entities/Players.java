package com.Football.Tournament.entities;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "Players")
public class Players {
	
	//PRIMARY KEY 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //AUTO GENERATING VALUES FOR ID COLUMN.
	private long id;
	@Column(nullable = false) //SETTING NOT NULL PROPERTY TO THE COLUMN
	private String name;
	@Column(nullable = false)
	private int age;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date created_at;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date updated_at;
	@Column(name="team_id" ,nullable = false)
	private long team_id;

	@PrePersist //THIS WILL RUN JUST BEFORE CREATION OF NEW PLAYER AND WILL SET THE DATE AND TIME OF CREATION
	private void onCreate() {
		created_at = new Date();//AT THE TIME OF CREATION :VALUE OF created_at=VALUE OF updated_at.
		updated_at = new Date();
		//SO BASICALLY created_at,updated_at AND Id WILL BE SET AUTOMATICALLY.
	}

	@PreUpdate //THIS WILL RUN JUST BEFORE UPDATION OF EXISTING PLAYER
	private void onUpadate() {
		updated_at = new Date();
	}

	public Players() {
		super();
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public long getTeam_id() {
		return team_id;
	}

	public void setTeam_id(long team_id) {
		this.team_id = team_id;
	}

	public Players(long id, String name, int age, Date created_at, Date updated_at, long team_id) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.team_id = team_id;
	}

}
