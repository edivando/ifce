/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.entity;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
public enum TipoUsuario {

	ADMINISTRADOR("home", 
				"perfil", 
				"aluno", 
				"empresa", 
				"instituicao", 
				"vaga_estagio", 
				"usuario", 
				"documento"), 
				
	INSTITUICAO("home", "perfil"), 
	
	ALUNO(	"homeAluno", 
			"perfil", 
			"completeCadastro", 
			"docFichaMatricula", 
			"docRelatorioDiario", 
			"docRelatorioFinalCursoSuperior", 
			"docRelatorioFinalCursoTecnico", 
			"docRelatorioPeriodicoAtividade", 
			"docRequerimentoConclusao", 
			"docTermoAditivo", 
			"docTermoCompromisso",
			"docTermoRealizacaoAvaliacaoEstagio");
	
	private String[] pages;
	
	private TipoUsuario(String...pages) {
		this.pages = pages;
	}
	
	public String[] getPages() {
		return pages;
	}
}
