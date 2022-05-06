package com.dionlan.gamesranking.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "total_games")
	private Integer totalGames = Integer.valueOf(0);
	
	@Column(name = "total_wins")
	private Integer totalWins = Integer.valueOf(0);
	
	public void setTotalWins(Integer totalWins) {
		///this.setTotalGames(totalWins);
		this.totalWins = totalWins;
	}
	
	public void setTotalGames(Integer totalGames) {
		this.totalGames = totalGames;
	}
	
	/*
	public Game(Integer totalGames, Integer totalWins) {
		super();
		this.totalGames = totalGames;
		this.totalWins = totalWins;
	}*/
}
