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
 * @date  11/02/2016
 * 
 */
public enum DocumentoKey {
	
	// Usuario
	USUARIO_NOME( 				"&&USUARIO_NOME&&" ),
	USUARIO_EMAIL( 				"&&USUARIO_EMAIL&&" ),
	
	// Aluno
	ALUNO_DESCRICAO( 			"&&ALUNO_DESCRICAO&&" ),
	ALUNO_MATRICULA( 			"&&ALUNO_MATRICULA&&" ),
	ALUNO_TELEFONE( 			"&&ALUNO_TELEFONE&&" ),
	ALUNO_CELULAR( 				"&&ALUNO_CELULAR&&" ),
	ALUNO_CV_LATTES( 			"&&ALUNO_CV_LATTES&&" ),
	ALUNO_CPF( 					"&&ALUNO_CPF&&" ),
	ALUNO_RG( 					"&&ALUNO_RG&&" ),
	ALUNO_DATA_NASCIMENTO( 		"&&ALUNO_DATA_NASCIMENTO&&" ),
	ALUNO_ENDERECO_ENDERECO(	"&&ALUNO_ENDERECO_ENDERECO&&" ),
	ALUNO_ENDERECO_NUMERO( 		"&&ALUNO_ENDERECO_NUMERO&&" ),
	ALUNO_ENDERECO_BAIRRO( 		"&&ALUNO_ENDERECO_BAIRRO&&" ),
	ALUNO_ENDERECO_CEP( 		"&&ALUNO_ENDERECO_CEP&&" ),
	ALUNO_ENDERECO_CIDADE( 		"&&ALUNO_ENDERECO_CIDADE&&" ),
	ALUNO_ENDERECO_UF( 			"&&ALUNO_ENDERECO_UF&&" ),
	
	// Empresa
	EMPRESA_NOME( 				"&&EMPRESA_NOME&&" ),
	EMPRESA_EMAIL( 				"&&EMPRESA_EMAIL&&" ),
	EMPRESA_TELEFONE( 			"&&EMPRESA_TELEFONE&&" ),
	EMPRESA_FAX( 				"&&EMPRESA_FAX&&" ),
	EMPRESA_SUPERVISOR( 		"&&EMPRESA_SUPERVISOR&&" ),
	EMPRESA_CARGO_SUPERVISOR(	"&&EMPRESA_CARGO_SUPERVISOR&&" ),
	EMPRESA_TELEFONE_SUPERVISOR("&&EMPRESA_TELEFONE_SUPERVISOR&&" ),
	EMPRESA_CNPJ( 				"&&EMPRESA_CNPJ&&" ),
	EMPRESA_SITE( 				"&&EMPRESA_SITE&&" ),
	EMPRESA_RAMO_ATIVIDADE( 	"&&EMPRESA_RAMO_ATIVIDADE&&" ),
	EMPRESA_ENDERECO_ENDERECO(	"&&EMPRESA_ENDERECO_ENDERECO&&" ),
	EMPRESA_ENDERECO_NUMERO(	"&&EMPRESA_ENDERECO_NUMERO&&" ),
	EMPRESA_ENDERECO_BAIRRO(	"&&EMPRESA_ENDERECO_BAIRRO&&" ),
	EMPRESA_ENDERECO_CEP( 		"&&EMPRESA_ENDERECO_CEP&&" ),
	EMPRESA_ENDERECO_UF( 		"&&EMPRESA_ENDERECO_UF&&" ),
	EMPRESA_ENDERECO_CIDADE(	"&&EMPRESA_ENDERECO_CIDADE&&" ),
	
	// Instituicao
	INSTITUICAO_NOME( 			"&&INSTITUICAO_NOME&&" ),
	
	// Campus
	CAMPUS_NOME( 				"&&CAMPUS_NOME&&" ),
	CAMPUS_EMAIL( 				"&&CAMPUS_EMAIL&&" ),
	CAMPUS_TELEFONE( 			"&&CAMPUS_TELEFONE&&" ),
	CAMPUS_ENDERECO_ENDERECO(	"&&CAMPUS_ENDERECO_ENDERECO&&" ),
	CAMPUS_ENDERECO_NUMERO( 	"&&CAMPUS_ENDERECO_NUMERO&&" ),
	CAMPUS_ENDERECO_BAIRRO( 	"&&CAMPUS_ENDERECO_BAIRRO&&" ),
	CAMPUS_ENDERECO_CEP( 		"&&CAMPUS_ENDERECO_CEP&&" ),
	CAMPUS_ENDERECO_UF( 		"&&CAMPUS_ENDERECO_UF&&" ),
	CAMPUS_ENDERECO_CIDADE( 	"&&CAMPUS_ENDERECO_CIDADE&&" ),
	
	// Departamento
	DEPARTAMENTO_NOME( 			"&&DEPARTAMENTO_NOME&&" ),
	
	// Curso
	ALUNO_CURSO_SEMESTRE_ATUAL( "&&ALUNO_CURSO_SEMESTRE_ATUAL&&" ),
	ALUNO_CURSO_NOME( 			"&&ALUNO_CURSO_NOME&&" );
	

	@Getter
	private String key;
	
	private DocumentoKey(String key) {
		this.key = key;
	}
	
	public static String getUsuario(){
		return new StringBuilder()
			.append(DocumentoKey.USUARIO_NOME.getKey()).append("<br/>")
			.append(DocumentoKey.USUARIO_EMAIL.getKey())
			.toString();
	}
	
	public static String getAluno(){
		return new StringBuilder()
			.append(DocumentoKey.ALUNO_DESCRICAO.getKey()).append("<br/>")
			.append(DocumentoKey.ALUNO_MATRICULA.getKey()).append("<br/>")
			.append(DocumentoKey.ALUNO_TELEFONE.getKey()).append("<br/>")
			.append(DocumentoKey.ALUNO_CELULAR.getKey()).append("<br/>")
			.append(DocumentoKey.ALUNO_CV_LATTES.getKey()).append("<br/>")
			.append(DocumentoKey.ALUNO_CPF.getKey()).append("<br/>")
			.append(DocumentoKey.ALUNO_RG.getKey()).append("<br/>")
			.append(DocumentoKey.ALUNO_DATA_NASCIMENTO.getKey()).append("<br/>")
			.append(DocumentoKey.ALUNO_ENDERECO_ENDERECO.getKey()).append("<br/>")
			.append(DocumentoKey.ALUNO_ENDERECO_NUMERO.getKey()).append("<br/>")
			.append(DocumentoKey.ALUNO_ENDERECO_BAIRRO.getKey()).append("<br/>")
			.append(DocumentoKey.ALUNO_ENDERECO_CEP.getKey()).append("<br/>")
			.append(DocumentoKey.ALUNO_ENDERECO_CIDADE.getKey()).append("<br/>")
			.append(DocumentoKey.ALUNO_ENDERECO_UF.getKey())
			.toString();
	}
	
	public static String getEmpresa(){
		return new StringBuilder()
			.append(DocumentoKey.EMPRESA_NOME.getKey()).append("<br/>")
			.append(DocumentoKey.EMPRESA_EMAIL.getKey()).append("<br/>")
			.append(DocumentoKey.EMPRESA_TELEFONE.getKey()).append("<br/>")
			.append(DocumentoKey.EMPRESA_FAX.getKey()).append("<br/>")
			.append(DocumentoKey.EMPRESA_SUPERVISOR.getKey()).append("<br/>")
			.append(DocumentoKey.EMPRESA_CARGO_SUPERVISOR.getKey()).append("<br/>")
			.append(DocumentoKey.EMPRESA_TELEFONE_SUPERVISOR.getKey()).append("<br/>")
			.append(DocumentoKey.EMPRESA_CNPJ.getKey()).append("<br/>")
			.append(DocumentoKey.EMPRESA_SITE.getKey()).append("<br/>")
			.append(DocumentoKey.EMPRESA_RAMO_ATIVIDADE.getKey()).append("<br/>")
			.append(DocumentoKey.EMPRESA_ENDERECO_ENDERECO.getKey()).append("<br/>")
			.append(DocumentoKey.EMPRESA_ENDERECO_NUMERO.getKey()).append("<br/>")
			.append(DocumentoKey.EMPRESA_ENDERECO_BAIRRO.getKey()).append("<br/>")
			.append(DocumentoKey.EMPRESA_ENDERECO_CEP.getKey()).append("<br/>")
			.append(DocumentoKey.EMPRESA_ENDERECO_UF.getKey()).append("<br/>")
			.append(DocumentoKey.EMPRESA_ENDERECO_CIDADE.getKey())
			.toString();
	}
	
	public static String getInstituicao(){
		return new StringBuilder()
			.append(DocumentoKey.INSTITUICAO_NOME.getKey())
			.toString();
	} 
	
	public static String getCampus(){
		return new StringBuilder()
			.append(DocumentoKey.CAMPUS_NOME.getKey()).append("<br/>")
			.append(DocumentoKey.CAMPUS_EMAIL.getKey()).append("<br/>")
			.append(DocumentoKey.CAMPUS_TELEFONE.getKey()).append("<br/>")
			.append(DocumentoKey.CAMPUS_ENDERECO_ENDERECO.getKey()).append("<br/>")
			.append(DocumentoKey.CAMPUS_ENDERECO_NUMERO.getKey()).append("<br/>")
			.append(DocumentoKey.CAMPUS_ENDERECO_BAIRRO.getKey()).append("<br/>")
			.append(DocumentoKey.CAMPUS_ENDERECO_CEP.getKey()).append("<br/>")
			.append(DocumentoKey.CAMPUS_ENDERECO_UF.getKey()).append("<br/>")
			.append(DocumentoKey.CAMPUS_ENDERECO_CIDADE.getKey())
			.toString();
	}
	
	public static String getDepartamento(){
		return new StringBuilder()
			.append(DocumentoKey.DEPARTAMENTO_NOME.getKey())
			.toString();
	}
	
	public static String getCurso(){
		return new StringBuilder()
			.append(DocumentoKey.ALUNO_CURSO_SEMESTRE_ATUAL.getKey()).append("<br/>")
			.append(DocumentoKey.ALUNO_CURSO_NOME.getKey())
			.toString();
	}
	

	

	
	

}
