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
@Table(name = "vaga_estagio_atividade_diaria")
@NamedQueries({ @NamedQuery(name = "VagaEstagioAtividadeDiaria", query = "Select v from VagaEstagioAtividadeDiaria v") })
public class VagaEstagioAtividadeDiaria {

	@Id
	@Getter @Setter
	private Integer idAtividade;
	
	@Getter @Setter
	private Date data;
	
	@Getter @Setter
	private String descricao;
	
	@Getter @Setter
	private Integer quantidadeHoras;
}
