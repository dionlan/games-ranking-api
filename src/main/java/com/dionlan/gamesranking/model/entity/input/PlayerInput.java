package com.dionlan.gamesranking.model.entity.input;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlayerInput {

	private String name;
	
	private String nickname;
	
	private GameInput game;
}
