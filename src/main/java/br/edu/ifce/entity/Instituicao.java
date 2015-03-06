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
@Table(name = "instituicao")
@NamedQueries({ @NamedQuery(name = "Instituicao", query = "Select i from Instituicao i") })
public class Instituicao implements IGenericEntity<Instituicao>{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer idInstiruicao;
	
	@Getter @Setter
	private String campus;
	
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private String responsavel;
}
