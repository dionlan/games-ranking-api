package com.dionlan.gamesranking.ranking;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Game {
	
	private Long id;
	private Integer totalGames = Integer.valueOf(0);
	private Integer totalWins = Integer.valueOf(0);
	
	public Game(Long id, Integer totalWins, Integer totalGames) {
		this.id = id;
		this.totalWins = totalWins;
		this.totalGames = totalGames;
	}
}
