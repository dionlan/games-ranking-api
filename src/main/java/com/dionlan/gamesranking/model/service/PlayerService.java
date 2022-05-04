package com.dionlan.gamesranking.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dionlan.gamesranking.model.entity.Game;
import com.dionlan.gamesranking.model.entity.Player;
import com.dionlan.gamesranking.model.exception.BusinessException;
import com.dionlan.gamesranking.model.exception.PlayerAlreadyRegisteredException;
import com.dionlan.gamesranking.model.exception.PlayerNotFoundException;
import com.dionlan.gamesranking.model.repository.PlayerRepository;

@Service
public class PlayerService {
	private static final String MSG_PLAYER_REGISTERED = "Player's nickname %s already registered. Choose other nickname.";
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private GameService gameService;
	
	public List<Player> allPlayers(){
		return playerRepository.findAll();
	}
	
	@Transactional
	public Player save(Player player){
		try {
			playerValidate(player);
			return playerRepository.save(player);
		} catch (DataIntegrityViolationException e) {
			throw new PlayerAlreadyRegisteredException(String.format(MSG_PLAYER_REGISTERED, player.getNickname()));
		}
	}
	
	public Player findOrException(Long playerId) {
		return playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException(playerId));
	}
	
	public Player getByNickname(String nickname){
		validarNickname(nickname);
		return playerRepository.findByNickname(nickname);
	}
	
	private void validarNickname(String nickname) {
		boolean existe = playerRepository.existsByNickname(nickname);
		if(!existe) {
			throw new BusinessException("Nenhum nickname encontrado.");
		}
	}
	
	public Player incrementWins(Player player) {
		gameValidate(player);
		Long playerId = player.getGame().getId();
		Game game = gameService.findOrException(playerId);
		player.setGame(game);
		return playerRepository.save(player);
	}
	
	public Player incrementGames(Player player) {
		gameValidate(player);
		Long playerId = player.getGame().getId();
		Game game = gameService.findOrException(playerId);
		player.setGame(game);
		return playerRepository.save(player);
	}
	
	private void playerValidate(Player player) {
		if(player.getName() == null || player.getName().equals("")) {
			throw new BusinessException("Informe um valor para o nome");
		}
		if(player.getNickname() == null || player.getNickname().equals("")) {
			throw new BusinessException("Informe um valor para o apelido. Não pode conter espaços em branco");
		}
	}
	
	private void gameValidate(Player player) {
		if(player.getGame().getTotalGames() < player.getGame().getTotalWins()) {
			throw new BusinessException("O total de partidas não pode ser inferior ao total de vitórias");
		}
		if(player.getGame().getTotalWins() == null || player.getGame().getTotalWins() < 0) {
			throw new BusinessException("O total de vitórias não pode ser inferior a 0");
		}
		if(player.getGame().getTotalGames() == null || player.getGame().getTotalWins() < 1) {
			throw new BusinessException("O total de partidas não pode ser inferior a 1");
		}
	}
}
