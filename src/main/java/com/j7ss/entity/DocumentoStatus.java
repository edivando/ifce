/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.entity;

import lombok.Getter;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
public enum DocumentoStatus {

	INDISPONIVEL("fa-times","", "Não Disponível"), 
	DISPONIVEL("fa-check", "blue", "Disponível"), 
	AGUARDANDO_VERIFICACAO("fa-clock-o", "yellow", "Aguardando verificação da instituição"), 
	VERIFICANDO("fa-play-circle-o", "purple", "Verificando"), 
	VERIFICADO_COM_ERRO("fa-warning", "red", "Verificado com erros"), 
	DISPONIVEL_DOWNNLOAD("fa-download", "green", "Disponível para Download");

	@Getter
	private String icon;
	@Getter
	private String titulo;
	@Getter
	private String cor;
	
	private DocumentoStatus(String icon, String cor, String titulo) {
		this.icon = icon;
		this.cor = cor;
		this.titulo = titulo;
	}
}
