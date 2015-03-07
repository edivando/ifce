package br.edu.ifce.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import br.edu.ifce.util.entity.IGenericEntity;

/**
 * 
 * 
 * 
 * @author edivandoalves
 *
 */
@Entity
@Table(name = "endereco")
public class Endereco implements IGenericEntity<Endereco>{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer idEndereco;
	
	@Getter @Setter
	private String endereco;
	
	@Getter @Setter
	private String bairro;
	
	@Getter @Setter
	private String cep;
	
	@Getter @Setter
	private String uf;
	
	@Getter @Setter
	private String cidade;
}
