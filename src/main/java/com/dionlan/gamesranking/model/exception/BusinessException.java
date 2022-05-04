package com.dionlan.gamesranking.model.exception;

/*
 * Exception mais genérica para tratar qualquer erro da camada de negócio (Serviço) que não seja específico
 */

public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public BusinessException(String mensagem) {
		super(mensagem);
	}

	public BusinessException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
