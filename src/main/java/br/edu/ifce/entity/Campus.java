package br.edu.ifce.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "campus")
@NamedQueries({ @NamedQuery(name = "Campus", query = "Select c from Campus c") })
public class Campus {

	private Integer idCampus;
	private String nome;
}
