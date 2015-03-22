package br.edu.ifce.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import br.edu.ifce.util.entity.IGenericEntity;

/**
 * 
 * 
 * 
 * @author edivandoalves
 *
 */
@Entity
@Table(name = "aluno")
@ToString @EqualsAndHashCode
public class Aluno implements IGenericEntity<Aluno>{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer idAluno;
	
	@Getter @Setter
	private Integer idUsuario;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String descricao;
	
	@Getter @Setter
	private String matricula;
	
	@Getter @Setter
	private String telefone;
	
	@Getter @Setter
	private String habilidades;
	
	@Getter @Setter
	private String cvLattes;
	
	@Getter @Setter
	private Integer semestreAtual;
	
	@Getter @Setter
	private String cpf;
	
	@Getter @Setter
	private String rg;
	
	@Getter @Setter
	private Date dataNascimento;
	
	@ManyToOne
	@Getter @Setter
	private Curso curso;
	
	@OneToOne
	@Getter @Setter
	private Endereco endereco;
	
	public String getDescricaoCurso(){
		return String.format("%s <br/> Departamento de  %s <br/> Campus %s <br/> %s", curso.getNome(), curso.getDepartamento().getNome(), 
			curso.getDepartamento().getCampus().getNome(), curso.getDepartamento().getCampus().getInstituicao().getNome());
	}
	
}
