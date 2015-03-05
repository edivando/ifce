package br.edu.ifce.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "curso")
@NamedQueries({ @NamedQuery(name = "Curso", query = "Select c from Curso c") })
public class Curso {

	private Integer idCurso;
	private String nomeCurso;
	private String de;
}
