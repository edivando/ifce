package com.j7ss.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.j7ss.util.DAO;
import com.j7ss.util.DAOException;
import com.j7ss.util.IGenericEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
	private String descricao;
	@Getter @Setter
	private String matricula;
	@Getter @Setter
	private String telefone;
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
	private Usuario usuario;

	// Endereco
	@Getter @Setter
	private String endereco;
	@Getter @Setter
	private String bairro;
	@Getter @Setter
	private String cep;
	@Getter @Setter
	private String cidade;
	@Getter @Setter
	private String uf;


//	public Aluno(Integer idUsuario, String nome){
//		this.idUsuario = idUsuario;
////		this.nome = nome;
//	}
	
	public String getDescricaoCurso(){
		String cursoName = "";
		String departamentoName = "";
		String campusName = "";
		String instituicaoName = "";
		if(curso != null){
			cursoName = curso.getNome();
			if(curso.getDepartamento() != null){
				departamentoName = curso.getDepartamento().getNome();
				if(curso.getDepartamento().getCampus() != null){
					campusName = curso.getDepartamento().getCampus().getNome();
					if(curso.getDepartamento().getCampus().getInstituicao() != null){
						instituicaoName = curso.getDepartamento().getCampus().getInstituicao().getNome();
					}
				}
			}
		}
		return String.format("%s <br/> Departamento de  %s <br/> Campus %s <br/> %s", cursoName, departamentoName, campusName, instituicaoName);
	}
	
	@Override
	public boolean isNew() {
		return idAluno == null;
	}
	
	
//## Builder
	public Aluno idAluno(Integer idAluno){
		this.idAluno = idAluno;
		return this;
	}
	
//	public Aluno idUsuario(Integer idUsuario){
//		this.idUsuario = idUsuario;
//		return this;
//	}
	
//	public Aluno nome(String nome){
//		this.nome = nome;
//		return this;
//	}
	
	public Aluno descricao(String descricao){
		this.descricao = descricao;
		return this;
	}
	
	public Aluno matricula(String matricula){
		this.matricula = matricula;
		return this;
	}
	
	public Aluno cvLattes(String cvLattes){
		this.cvLattes = cvLattes;
		return this;
	}
	
	public Aluno semestreAtual(Integer semestreAtual){
		this.semestreAtual = semestreAtual;
		return this;
	}
	
	public Aluno cpf(String cpf){
		this.cpf = cpf;
		return this;
	}
	
	public Aluno rg(String rg){
		this.rg = rg;
		return this;
	}
	
	public Aluno dataNascimento(Date dataNascimento){
		this.dataNascimento = dataNascimento;
		return this;
	}
	
//## DAO
	private static DAO<Aluno> dao = new DAO<Aluno>(Aluno.class);
	
	@Override
	public Aluno save() throws DAOException{
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(idAluno);
	}
	
	public static List<Aluno> findAll(){
		return dao.findAll();
	}
	
	public static Long countAll(){
		return dao.countAll();
	}
	
	public static Aluno findByUsuario(Usuario usuario){
		return dao.findOneByQuery("SELECT a FROM Aluno a WHERE a.usuario = ?1" , usuario);
	}
	
}
