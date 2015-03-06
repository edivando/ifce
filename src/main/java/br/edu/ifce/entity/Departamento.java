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
@Table(name = "departamento")
@NamedQueries({ @NamedQuery(name = "Departamento", query = "Select d from Departamento d") })
public class Departamento implements IGenericEntity<Departamento> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer idDepartamento;
	
	@Getter @Setter
	private String nome;
}
