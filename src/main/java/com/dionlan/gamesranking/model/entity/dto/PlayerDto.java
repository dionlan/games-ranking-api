package com.dionlan.gamesranking.model.entity.dto;

import com.dionlan.gamesranking.model.entity.Game;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlayerDto {
	
	private String name;
	private String nickname;
	private Game game;
}
