package br.edu.ifce.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
@NamedQueries({ @NamedQuery(name = "Usuario", query = "Select u from Usuario u") })
public class Usuario {

	private Integer idUsuario;
	private String nome;
	private String email;
	private String senha;
	private Boolean emailValido;
	
}
