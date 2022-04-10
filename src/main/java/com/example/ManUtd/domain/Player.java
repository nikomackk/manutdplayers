package com.example.ManUtd.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Player {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//@NotNull
	private Long id;
	
	@Size(min=2, max=50)
	private String name;
	
	@Min(1)
	@Max(99)
	private Integer number;
	
	
	@ManyToOne
	@JsonIgnoreProperties("players")
	@JoinColumn(name = "nationalityid")
	private Nationality nationality;
	
	
	@ManyToOne
	@JsonIgnoreProperties("players")
	@JoinColumn(name = "positionid")
	private Position position;
	
	public Player() {}

	public Player(String name, Integer number, Nationality nationality, Position position) {
		super();
		this.name = name;
		this.number = number;
		this.nationality = nationality;
		this.position = position;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getNumber() {
		return number;
	}

	public Nationality getNationality() {
		return nationality;
	}

	public Position getPosition() {
		return position;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public void setNationality(Nationality nationality) {
		this.nationality = nationality;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public String toString() {
		if (this.nationality != null || this.position != null) {
			return "Player [id=" + id + ", name=" + name + ", number=" + number + ", nationality=" + this.getNationality()
					+ ", position=" + this.getPosition() + "]";
		} else {
			return "Player [id=" + id + ", name=" + name + ", number=" + number + "]";
		}
		
	}
	
	
	
	
	
	
}
