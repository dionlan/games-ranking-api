package com.dionlan.gamesranking.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	MENSAGEM_INCOMPREENSIVEL ("/incomprehensible-message", "Message incomprehensible"),
	
	ENTIDADE_NAO_ENCONTRADA ("/entity-not-found", "Entity not found"),
	
	ENTIDADE_EM_USO ("/entity-already-registered", "Entity already regitered"), 
	
	ERRO_NEGOCIO("/business-error", "Business rules violation"),
	
	RECURSO_NAO_ENCONTRADO("/resource-not-found", "URL Resource not found"),
	
	ERRO_SISTEMA("/internal-error", "Internal error"),
	
	PARAMETRO_INVALIDO("/invalid-parameter", "Invalid parameter"),
	
	DADOS_INVALIDOS("/indalid-data", "Invalid data");
	
	private String title;
	
	private String uri;
	
	 private ProblemType(String path, String title) {
		 this.uri = "https://gamesranking.com" + path;
		 this.title = title;
	 }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
