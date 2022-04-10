package com.example.ManUtd.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Nationality {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long nationalityid;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "nationality")
	private List<Player> players;
	
	public Nationality() {}

	public Nationality(String name) {
		super();
		this.name = name;
	}

	public long getNationalityid() {
		return nationalityid;
	}

	public String getName() {
		return name;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setNationalityid(long nationalityid) {
		this.nationalityid = nationalityid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		return "Nationality [nationalityid=" + nationalityid + ", name=" + name + "]";
	}
	
	
	
	
}
