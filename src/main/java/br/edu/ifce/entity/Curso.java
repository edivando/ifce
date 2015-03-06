package br.edu.ifce.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import br.edu.ifce.util.entity.IGenericEntity;

@Entity
@Table(name = "curso")
@NamedQueries({ @NamedQuery(name = "Curso", query = "Select c from Curso c") })
public class Curso implements IGenericEntity<Curso>{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer idCurso;
	
	@Getter @Setter
	private String nomeCurso;
	
	@Getter @Setter
	private String de;
}
