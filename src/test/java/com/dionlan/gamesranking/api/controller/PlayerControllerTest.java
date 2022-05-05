package com.dionlan.gamesranking.api.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.dionlan.gamesranking.model.entity.Game;
import com.dionlan.gamesranking.model.entity.Player;
import com.dionlan.gamesranking.model.entity.input.GameInput;
import com.dionlan.gamesranking.model.entity.input.PlayerInput;
import com.dionlan.gamesranking.model.exception.PlayerAlreadyRegisteredException;
import com.dionlan.gamesranking.model.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerTest {
	
	static final String API = "/api/players";
	static final MediaType JSON = MediaType.APPLICATION_JSON;
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private PlayerService playerService;
	
	@Test
	public void deveRetornarUmIsCreated_QuandoCriarUmNovoJogador() throws Exception {
		// Cenario
		String nome = "Novo Jogador";
		String nickname = "novojogador";
		Integer totalVitorias = 20;
		Integer totalPartidas = 35;
		
		PlayerInput dto = criaNovoJogadorInput();
		
		Player player = new Player();
		Game game = new Game();
		player.setName(nome);
		player.setNickname(nickname);
		game.setTotalWins(totalVitorias);
		game.setTotalGames(totalPartidas);
		player.setGame(game);
		
		Mockito.when(playerService.save(Mockito.any(Player.class))).thenReturn(player);
		
		String json = new ObjectMapper().writeValueAsString(dto);
		
		//Verificacao // Execucao
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(API.concat("/save"))
											.accept(JSON)
											.contentType(JSON)
											.content(json);
		mvc
			.perform(request)
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("name").value(player.getName() ))
			.andExpect(MockMvcResultMatchers.jsonPath("nickname").value(player.getNickname() ))
			.andExpect(MockMvcResultMatchers.jsonPath("game.totalWins").value(player.getGame().getTotalWins()))
			.andExpect(MockMvcResultMatchers.jsonPath("game.totalGames").value(player.getGame().getTotalGames()))
			.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void deveRetornarUmBadRequest_QuandoTentarCriarUmJogadorExistente() throws Exception {
		// Cenario
		String nome = "Jogador Existente";
		String nickname = "dionlan";
		Integer totalVitorias = 20;
		Integer totalPartidas = 35;
		
		PlayerInput dto = criaNovoJogadorInput();
		
		Player player = new Player();
		Game game = new Game();
		player.setName(nome);
		player.setNickname(nickname);
		game.setTotalWins(totalVitorias);
		game.setTotalGames(totalPartidas);
		player.setGame(game);
		
		Mockito.when(playerService.save(Mockito.any(Player.class))).thenThrow(PlayerAlreadyRegisteredException.class);
		
		String json = new ObjectMapper().writeValueAsString(dto);
		
		//Verificacao // Execucao
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(API.concat("/save"))
											.accept(JSON)
											.contentType(JSON)
											.content(json);
		mvc
			.perform(request)
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	private PlayerInput criaNovoJogadorInput () {
		PlayerInput playerInput = new PlayerInput();
		GameInput gameInput = new GameInput();
		gameInput.setTotalWins(20);
		gameInput.setTotalGames(35);
		playerInput.setName("Dionlan");
		playerInput.setNickname("dionlan");
		playerInput.setGame(gameInput);
		
		return playerInput;
	}
}
