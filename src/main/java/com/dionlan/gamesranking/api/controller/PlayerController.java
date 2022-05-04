package com.dionlan.gamesranking.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dionlan.gamesranking.api.assembler.PlayerDtoAssembler;
import com.dionlan.gamesranking.api.assembler.PlayerInputDisassembler;
import com.dionlan.gamesranking.model.entity.Player;
import com.dionlan.gamesranking.model.entity.dto.PlayerDto;
import com.dionlan.gamesranking.model.entity.input.PlayerInput;
import com.dionlan.gamesranking.model.exception.BusinessException;
import com.dionlan.gamesranking.model.exception.PlayerNotFoundException;
import com.dionlan.gamesranking.model.service.PlayerService;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private PlayerInputDisassembler playerInputDisassembler;
	
	@Autowired
	private PlayerDtoAssembler playerDtoAssembler;
	
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public PlayerDto save(@RequestBody PlayerInput playerInput) {
		Player player = playerInputDisassembler.toDomainObject(playerInput);
		return playerDtoAssembler.toDto(playerService.save(player));
	}
	
	@PutMapping("/wins/{nickname}")
	public PlayerDto incrementTotalWins(@PathVariable String nickname, @RequestBody PlayerInput playerInput){
		try {
			Player currentPlayer = playerService.getByNickname(nickname);
			
			playerInputDisassembler.copyToDtoObject(playerInput, currentPlayer);
			
			return playerDtoAssembler.toDto(playerService.incrementWins(currentPlayer));
		
		}catch(PlayerNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
	}


}
