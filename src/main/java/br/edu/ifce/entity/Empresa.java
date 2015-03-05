package br.edu.ifce.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "empresa")
@NamedQueries({ @NamedQuery(name = "Empresa", query = "Select e from Empresa e") })
public class Empresa {

	private Integer idEmpresa;
	private String nome;
	private String email;
	private String telefone;
	private String supervisor;
	private String cargoSupervisor;
	private String telefoneSupervisor;
	private String cnpj;
	private String site;
	private String ramoAtividade;
}
