package br.edu.ifce.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "instituicao")
@NamedQueries({ @NamedQuery(name = "Instituicao", query = "Select i from Instituicao i") })
public class Instituicao {

	private Integer idInstiruicao;
	private String campus;
	private String email;
	private String responsavel;
}
