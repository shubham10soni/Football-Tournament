package com.Football.Tournament.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@javax.persistence.Entity
@Table(name = "Teams")
public class Teams {
	//PRIMARY KEY 
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)//AUTO GENERATING VALUES FOR ID COLUMN.
	private long id;
	@Column(nullable = false)//SETTING NOT NULL PROPERTY TO THE COLUMN
	private String name;
	@Column(nullable = false)
	private String location;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date created_at;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date updated_at;

	@PrePersist//THIS WILL RUN JUST BEFORE CREATION OF NEW PLAYER AND WILL SET THE DATE AND TIME OF CREATION
	private void onCreate() {
		created_at = new Date();//AT THE TIME OF CREATION :VALUE OF created_at=VALUE OF updated_at.
		updated_at = new Date();
		//SO BASICALLY created_at,updated_at AND Id WILL BE SET AUTOMATICALLY.
	}

	@PreUpdate//THIS WILL RUN JUST BEFORE UPDATION OF EXISTING PLAYER
	private void onUpadate() {
		updated_at = new Date();
	}
	//FOR ONE TO MANY UNIDIRECTIONAL MAPPING 
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "team_id", referencedColumnName = "id")//FOREIGN KEY
	private List<Players> player = new ArrayList<>();

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public Teams() {
		super();
		
	}

	public Teams(long id, String name, String location, Date created_at, Date updated_at) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

}
