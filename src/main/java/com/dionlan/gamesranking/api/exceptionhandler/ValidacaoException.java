package com.dionlan.gamesranking.api.exceptionhandler;

import org.springframework.validation.BindingResult;

public class ValidacaoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private BindingResult bindingResult;
	
	public ValidacaoException(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}
	
	public BindingResult getBindingResult() {
		return bindingResult;
	}
	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}
}