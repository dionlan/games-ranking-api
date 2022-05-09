package com.dionlan.gamesranking.model.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
	
	public Map<Integer, Player> allPlayers(){
		List<Player> players = playerRepository.findAll();
		AtomicInteger index = new AtomicInteger(1);
		Map<Integer, Player> nameMap =  players.stream()
		    .sorted(Comparator.comparing(t -> t.getGame().getTotalWins(), Comparator.reverseOrder()))
		    .collect(Collectors.toMap(value -> index.getAndIncrement(), e1 -> e1));
		return nameMap;
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
		playerValidate(player);
		Long playerId = player.getGame().getId();
		Game game = gameService.findOrException(playerId);
		player.setGame(game);
		return playerRepository.save(player);
	}
	
	public void playerValidate(Player player) {
		if(player.getName() == null || player.getName().equals("")) {
			throw new BusinessException("Informe um valor para o nome");
		}
		if(player.getNickname() == null || player.getNickname().equals("")) {
			throw new BusinessException("Informe um valor para o apelido. Nao pode conter espacos em branco");
		}
		if(player.getGame().getTotalWins() == null || player.getGame().getTotalWins() < 0) {
			throw new BusinessException("O total de vitorias nao pode ser inferior a 0");
		}
		if(player.getGame().getTotalGames() == null || player.getGame().getTotalGames() < 1) {
			throw new BusinessException("O total de partidas nao pode ser inferior a 1");
		}
		if(player.getGame().getTotalGames() < player.getGame().getTotalWins()) {
			throw new BusinessException("O total de partidas nao pode ser inferior ao total de vitorias");
		}

	}
}
