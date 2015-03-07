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
@Table(name = "usuario")
public class Usuario implements IGenericEntity<Usuario>{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer idUsuario;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private String senha;
	
	@Getter @Setter
	private Boolean emailValido;
	
}
