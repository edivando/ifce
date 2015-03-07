package br.edu.ifce.entity;

public enum TipoUsuario {

	ADMINISTRADOR("home,perfil,aluno,campus,curso,departamento,empresa,instituicao,vaga_estagio"), 
	INSTITUICAO("home"), 
	EMPRESA("home"), 
	ALUNO("home");
	
	private String page;
	
	private TipoUsuario(String page) {
		this.page = page;
	}
	
	public String[] getPages() {
		return page.split(",");
	}
}
