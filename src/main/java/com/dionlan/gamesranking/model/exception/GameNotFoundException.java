package com.dionlan.gamesranking.model.exception;

public class GameNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public GameNotFoundException(String mensagem) {
		super(mensagem);
	}

	public GameNotFoundException(Long gamerId) {
		this(String.format("Not exist register for Game's Id %d ", gamerId));
	}
}
