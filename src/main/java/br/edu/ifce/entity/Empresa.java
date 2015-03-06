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
@Table(name = "empresa")
@NamedQueries({ @NamedQuery(name = "Empresa", query = "Select e from Empresa e") })
public class Empresa implements IGenericEntity<Empresa>{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer idEmpresa;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private String telefone;
	
	@Getter @Setter
	private String supervisor;
	
	@Getter @Setter
	private String cargoSupervisor;
	
	@Getter @Setter
	private String telefoneSupervisor;
	
	@Getter @Setter
	private String cnpj;
	
	@Getter @Setter
	private String site;
	
	@Getter @Setter
	private String ramoAtividade;
}
