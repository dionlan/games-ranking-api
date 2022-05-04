package com.dionlan.gamesranking.model.entity.input;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GameInput {

	private Integer totalGames;
	
    @NonNull
    @JsonProperty(required = true) 
	private Integer totalWins;
}
