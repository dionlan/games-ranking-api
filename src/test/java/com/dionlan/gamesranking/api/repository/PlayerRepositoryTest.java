package com.dionlan.gamesranking.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dionlan.gamesranking.model.entity.Game;
import com.dionlan.gamesranking.model.entity.Player;
import com.dionlan.gamesranking.model.repository.PlayerRepository;

/*
 * * Refatorado: os metodos de incrementar foram extraidos para a camada de servico
 */
@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test") 
public class PlayerRepositoryTest {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void deveCriarESalvarUmJogador() {
		// Cenario criado
		Player player = criarEPersistirJogador();
		
		// Execucao
		player = playerRepository.save(player);
		
		// Verificacao
		assertThat(player.getId()).isNotNull();
	}
	
	@Test
	public void deveIncrementarAQuantidadeDeVitorias() {
		// Cenario: cria e recupera um jogador para ser deletado
		Player player = criarEPersistirJogador();
		player.setNickname("nicknameNovo");
		player.getGame().setTotalWins(30); //sera somada 10 vitorias no cenario informado de 20, devendo retornar 30
		
		// Execucao
		playerRepository.save(player);
		Player jogadorComVitoriaIncrementada = entityManager.find(Player.class, player.getId());
		
		//Verificacao 
		assertThat(jogadorComVitoriaIncrementada.getNickname()).isEqualTo("nicknameNovo");
		assertThat(jogadorComVitoriaIncrementada.getGame().getTotalWins()).isEqualTo(30);
	}
	
	/**
	 * Refatorado: os metodos de incrementar foram extraidos para a camada de servico
	 * No teste anterior o resultado total de vitorias se tortou 30
	 * Para testar o incremento de partidas, deve-ser somar o valor de vitorias ao total de partidas, que no caso eh 35 resultado em 65
	 */
	@Test
	public void deveIncrementarAQuantidadeDePartidas() {
		// Cenario: cria e recupera um jogador para ser deletado
		Player player = criarEPersistirJogador();
		player.setNickname("nicknameNovo");
		player.getGame().setTotalGames(45); 
		
		// Execucao
		playerRepository.save(player);
		Player jogadorComPartidaIncrementada = entityManager.find(Player.class, player.getId());
		
		//Verificacao 
		assertThat(jogadorComPartidaIncrementada.getNickname()).isEqualTo("nicknameNovo");
		assertThat(jogadorComPartidaIncrementada.getGame().getTotalGames()).isEqualTo(45);
	}
	
	private Player criarEPersistirJogador() {
		Player player = new Player();
		Game game = new Game();
		game.setTotalWins(20);
		game.setTotalGames(35);
		player.setName("Dionlan");
		player.setNickname("nicknameNovo");
		player.setGame(game);
		entityManager.persist(player);
		return player;
	}
}