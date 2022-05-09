package com.dionlan.gamesranking.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;

import com.dionlan.gamesranking.model.entity.Game;
import com.dionlan.gamesranking.model.entity.Player;
import com.dionlan.gamesranking.model.exception.BusinessException;
import com.dionlan.gamesranking.model.repository.PlayerRepository;
import com.dionlan.gamesranking.model.service.GameService;
import com.dionlan.gamesranking.model.service.PlayerService;

@SpringBootTest
@TestPropertySource("/application-test.properties")
public class PlayerServiceTest {

	@SpyBean
	private PlayerService playerService;
	
	@MockBean
	private PlayerRepository playerRepository;
	
	
	@Autowired
	private GameService gameService;
	
	@Test
	public void deveSalvarUmJogador_QuandoNaoHouverErroDeValidacao() {
		//cenário
		Player jogadorASalvar = criarJogador();
		Mockito.doNothing().when(playerService).playerValidate(jogadorASalvar);
		
		Player jogadorSalvo = criarJogador();
		jogadorSalvo.setName("novojogador");
		Mockito.when(playerRepository.save(jogadorASalvar)).thenReturn(jogadorASalvar);
		
		//ação / execução
		Player player = playerService.save(jogadorASalvar);
		assertThat(player.getId()).isEqualTo(jogadorSalvo.getId());
		assertThat(player.getNickname()).isEqualTo(jogadorSalvo.getNickname());
	}
	
	@Test
	public void naoDeveSalvarUmJogador_QuandoHouverErroDeValidacao() {
		//cenário
		Player jogadorASalvar = criarJogador();
		Mockito.doThrow(BusinessException.class).when(playerService).playerValidate(jogadorASalvar);
		
		//execução e verificação
		catchThrowableOfType(() -> playerService.save(jogadorASalvar), BusinessException.class);
		Mockito.verify(playerRepository, Mockito.never()).save(jogadorASalvar);
	}
	
	@Test
	public void obterJogadorPorNickname() {
		//cenário
		String nickname = "dionlan";
		
		Player jogador = criarJogador();
		jogador.setId(21L);
		jogador.setName(nickname);
		
		Mockito.when(playerRepository.findByNickname(nickname)).thenReturn(jogador);
		
		//Ação / Execução
		Player resultado = playerRepository.findByNickname(nickname);
		
		//Verificação
		assertThat(resultado).isNotNull();
	}
	
	@Test
	public void deveRetornarVazioQuandoOJogadorNaoExiste() {
		
		//cenário
		Long id = 1L;
		
		Player jogador = criarJogador();
		jogador.setId(id);
		
		Mockito.when(playerRepository.findById(id)).thenReturn(Optional.empty());
		
		//Ação / Execução
		Optional<Player> resultado = playerRepository.findById(id);
		
		//Verificação
		assertThat(resultado.isPresent()).isFalse();
	}
	
	@Test
	public void deveLancarErrosAoValidarJogador() {
		Player jogador = new Player();
		Game partida = new Game();
		
		Throwable erro = catchThrowable(() -> playerService.playerValidate(jogador));
		assertThat(erro).isInstanceOf(BusinessException.class).hasMessage("Informe um valor para o nome");
		
		jogador.setName("Jogador mock");
		
		erro = catchThrowable(() -> playerService.playerValidate(jogador));
		assertThat(erro).isInstanceOf(BusinessException.class).hasMessage("Informe um valor para o apelido. Nao pode conter espacos em branco");
		
		jogador.setNickname("jogador98");
		
		partida.setTotalGames(10);
		partida.setTotalWins(-1);
		jogador.setGame(partida);
		erro = catchThrowable(() -> playerService.playerValidate(jogador));
		assertThat(erro).isInstanceOf(BusinessException.class).hasMessage("O total de vitorias nao pode ser inferior a 0");
		partida.setTotalWins(11);
		
		partida.setTotalGames(-1);
		partida.setTotalWins(11);
		jogador.setGame(partida);
		erro = catchThrowable(() -> playerService.playerValidate(jogador));
		assertThat(erro).isInstanceOf(BusinessException.class).hasMessage("O total de partidas nao pode ser inferior a 1");
		partida.setTotalGames(10);
		
		partida.setTotalGames(10);
		partida.setTotalWins(11);
		jogador.setGame(partida);
		erro = catchThrowable(() -> playerService.playerValidate(jogador));
		assertThat(erro).isInstanceOf(BusinessException.class).hasMessage("O total de partidas nao pode ser inferior ao total de vitorias");
		
		partida.setTotalGames(11);
		partida.setTotalWins(10);
		jogador.setGame(partida);
	}
	
	public Player criarJogador() {
		Player jogador = new Player();
		jogador.setName("Dionlan");
		jogador.setNickname("dionlan");
		jogador.setGame(obterPartida());

		return jogador;
	}
	
	public Game obterPartida() {
		Game partidaOptional = gameService.findOrException(21L);
		
		Player jogador = new Player();
		jogador.setGame(partidaOptional);

		Game partida = new Game();
		partida.setId(partidaOptional.getId());
		partida.setTotalWins(partidaOptional.getTotalWins());
		partida.setTotalGames(partidaOptional.getTotalGames());
		
		return partida;
	}
}
