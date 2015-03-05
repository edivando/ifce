package br.edu.ifce.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "aluno")
@NamedQueries({ @NamedQuery(name = "Aluno", query = "Select a from Aluno a") })
public class Aluno {

	@Id
	@Getter @Setter
	private Integer idAluno;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String matricula;
	
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private String telefone;
	
	@Getter @Setter
	private String habilidades;
	
	@Getter @Setter
	private String cvLattes;
	
	@Getter @Setter
	private Integer semestreAtual;
	
	@Getter @Setter
	private String cpf;
	
	@Getter @Setter
	private String rg;
	
	@Getter @Setter
	private Date dataNascimento;
	
}
