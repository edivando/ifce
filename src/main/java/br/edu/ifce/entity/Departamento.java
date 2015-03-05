package br.edu.ifce.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "departamento")
@NamedQueries({ @NamedQuery(name = "Departamento", query = "Select d from Departamento d") })
public class Departamento {

	private Integer idDepartamento;
	private String nome;
}
