package com.dionlan.gamesranking.model.exception;

public class PlayerNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public PlayerNotFoundException(String mensagem) {
		super(mensagem);
	}

	public PlayerNotFoundException(Long playerId) {
		this(String.format("Not exist register for Player's Id %d ", playerId));
	}
}
