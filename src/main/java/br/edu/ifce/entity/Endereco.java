package br.edu.ifce.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "endereco")
@NamedQueries({ @NamedQuery(name = "Endereco", query = "Select e from Endereco e") })
public class Endereco {

	private Integer idEndereco;
	private String endereco;
	private String bairro;
	private String cep;
	private String uf;
	private String cidade;
}
