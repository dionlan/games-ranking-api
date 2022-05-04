package com.dionlan.gamesranking.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dionlan.gamesranking.model.entity.Player;
import com.dionlan.gamesranking.model.entity.input.PlayerInput;

@Component
public class PlayerInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Player toDomainObject(PlayerInput playerInput) {
		
		return modelMapper.map(playerInput, Player.class);
	}
	
	public void copyToDtoObject(PlayerInput playerInput, Player player) {
		//player.setGame(new Game());
		modelMapper.map(playerInput, player);
	}
}
