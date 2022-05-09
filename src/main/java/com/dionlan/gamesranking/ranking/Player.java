package com.dionlan.gamesranking.ranking;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import static java.util.Arrays.asList;

@Setter
@Getter
@Data
public class Player {
	
	private String name;
	private String nickname;
	private Game game;
	
	public Player(String name, String nickname, Game game) {
		super();
		this.name = name;
		this.nickname = nickname;
		this.game = game;
	}

	public static List<Player> bancoDePessoas(){
		return asList(
				new Player("Dionlan", "dionlan", new Game(1L, 20, 20)),
				new Player("Brenda", "brenda123", new Game(2L, 15, 10)),
				new Player("Marilia", "ma_548", new Game(3L, 16, 9)),
				new Player("Julia", "juju.alves", new Game(4L, 35, 20)),
				new Player("Priscila", "casinha123", new Game(5L, 14, 3)),
				new Player("Thiago", "ti-barbosa", new Game(6L, 48, 0)),
				new Player("Rita", "rita_1999", new Game(7L, 2, 2)),
				new Player("Felipe", "felipe123", new Game(8L, 10, 2)),
				new Player("Joao", "joaopaulo", new Game(9L, 50, 20))			
				);
	}
	
}
