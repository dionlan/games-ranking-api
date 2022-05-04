package com.dionlan.gamesranking.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dionlan.gamesranking.model.entity.Player;
import com.dionlan.gamesranking.model.entity.dto.PlayerDto;

/**
 * Classe montadora de retaurante model converter a dto para entity
 * @author Dionlan
 *
 */
@Component
public class PlayerDtoAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public PlayerDto toDto(Player player) {
		
		return modelMapper.map(player, PlayerDto.class);
	}
	
	public List<PlayerDto> toColletionModel(List<Player> players){
		return players.stream()
				.map(player -> toDto(player))
				.collect(Collectors.toList());
	}
}
