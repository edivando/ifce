package br.edu.ifce.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "vaga_estagio")
@NamedQueries({ @NamedQuery(name = "VagaEstagio", query = "Select v from VagaEstagio v") })
public class VagaEstagio {
	
	enum Turno{ MANHA, TARDE, NOITE} 

	private Integer idVaga;
	private String nome;
	private String area;
	private String requisitos;
	private String atividades;
	private String beneficios;
	private Integer cargaHoraria;
	private Double remuneracao;
	private Turno turno;
	
}
