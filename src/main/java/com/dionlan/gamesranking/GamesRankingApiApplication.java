package com.dionlan.gamesranking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GamesRankingApiApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(GamesRankingApiApplication.class, args);
	}
}