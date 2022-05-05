package com.dionlan.gamesranking.api;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.dionlan.gamesranking.model.entity.input.GameInput;
import com.dionlan.gamesranking.model.entity.input.PlayerInput;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

/**
 * A partir de out/nov de 2019 a vers�o 2.2 do Spring Boot implementa o JUnit 5 com as novas anota��es
 * @author Dionlan
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class PlayerCadastroIT {
	
	@LocalServerPort
	private int port;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/api/players/save";
		preparDados();
	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarJogadores() {
		RestAssured.given()
			.basePath("/api/players")
			.port(8080)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveRetornarStatus201_QuandoCadastrarJogador() {
		given()
			.body("{\"name\":\"TestPlayer\",\"nickname\":\"nick124\",\"game\":{\"totalGames\":10,\"totalWins\":10}}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	private void preparDados() {
		PlayerInput playerInput = new PlayerInput();
		GameInput gameInput = new GameInput();
		gameInput.setTotalWins(20);
		gameInput.setTotalGames(35);
		playerInput.setName("Dionlan");
		playerInput.setNickname("dionlan");
		playerInput.setGame(gameInput);
	}
}
