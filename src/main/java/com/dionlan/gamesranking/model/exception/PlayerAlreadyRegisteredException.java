package com.dionlan.gamesranking.model.exception;

public class PlayerAlreadyRegisteredException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public PlayerAlreadyRegisteredException(String mensagem) {
		super(mensagem);
	}

	public PlayerAlreadyRegisteredException(Long playerId) {
		this(String.format("Not exist register for Player's Id %d ", playerId));
	}
}
