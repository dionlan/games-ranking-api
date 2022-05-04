package com.dionlan.gamesranking.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "total_games")
	@NotBlank(message = "Informe a quantidade de partidas")
	private Integer totalGames;
	
	@Column(name = "total_wins")
	private Integer totalWins;
	
	public void setTotalWins(Integer total) {
		this.totalWins += total;
	}
	
	public void setTotalGames(Integer total) {
		this.totalGames += total;
	}
	
	/*
	public Game(Integer totalGames, Integer totalWins) {
		super();
		this.totalGames = totalGames;
		this.totalWins = totalWins;
	}*/
}
