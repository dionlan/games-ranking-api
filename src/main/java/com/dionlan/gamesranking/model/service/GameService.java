package com.dionlan.gamesranking.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dionlan.gamesranking.model.entity.Game;
import com.dionlan.gamesranking.model.exception.GameNotFoundException;
import com.dionlan.gamesranking.model.repository.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;
	
	public Game findOrException(Long gameId) {
		return gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException(gameId));
	}
}
