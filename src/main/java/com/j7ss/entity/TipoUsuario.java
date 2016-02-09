package com.j7ss.entity;

public enum TipoUsuario {

	ADMINISTRADOR("home", "perfil", "aluno", "empresa", "instituicao", "vaga_estagio", "usuario"), 
	INSTITUICAO("home", "perfil"), 
	ALUNO("home", "perfil", "termoCompromisso");
	
	private String[] pages;
	
	private TipoUsuario(String...pages) {
		this.pages = pages;
	}
	
	public String[] getPages() {
		return pages;
	}
}
