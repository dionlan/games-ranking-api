package com.dionlan.gamesranking.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "player")
public class Player implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@JsonIgnore
	@NotBlank
	@Column(nullable = false)
	private String name;
	
	//@JsonIgnore
	@NotBlank
	@Column(name = "nick_name", unique = true, nullable = false)
	private String nickname;

	@OneToOne(cascade = CascadeType.ALL)
	private Game game;
	
	/*
	public Player(String name, String nickname, Game game) {
		super();
		this.name = name;
		this.nickname = nickname;
		this.game = game;
	}*/
	
	
	/*
	public static List<Player> bancoDePessoas(){
		return asList(
				new Player("Dionlan", "dionlan", new Game(20, 20)),
				new Player("Brenda", "brenda123", new Game(15, 10)),
				new Player("Marilia", "ma_548", new Game(16, 9)),
				new Player("Julia", "juju.alves", new Game(35, 20)),
				new Player("Priscila", "casinha123", new Game(14, 3)),
				new Player("Thiago", "ti-barbosa", new Game(48, 0)),
				new Player("Rita", "rita_1999", new Game(2, 2)),
				new Player("Felipe", "felipe123", new Game(10, 2)),
				new Player("Joao", "joaopaulo", new Game(50, 20))			
				);
	}
	
	public String toString() {
        String header = "Nick" + getNickname() + " Wins: " + getGame().getTotalWins() + " Games: " + getGame().getTotalGames() + System.lineSeparator();
        return header;
		
	}*/
}
