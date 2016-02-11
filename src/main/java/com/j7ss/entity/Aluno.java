/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.j7ss.util.DAO;
import com.j7ss.util.DAOException;
import com.j7ss.util.IGenericEntity;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
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
	@Setter
	private Curso curso;
	@OneToOne
	@Setter
	private Usuario usuario;
	@OneToOne
	@Setter
	private VagaEstagio vagaEstagio;
	
	@OneToMany(mappedBy="aluno", fetch=FetchType.EAGER)
	@OrderBy("ordem")
	@Setter
	private List<DocumentoAluno> documentosAluno;

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
	
	public Curso getCurso() {
		return curso == null ? curso = new Curso() : curso;
	}
	
	public Usuario getUsuario() {
		return usuario == null ? usuario = new Usuario() : usuario;
	}
	
	public VagaEstagio getVagaEstagio() {
		return vagaEstagio == null ? vagaEstagio  = new VagaEstagio() : vagaEstagio;
	}
	
	public List<Documento> getDocumentos(){
		List<Documento> documentos = new ArrayList<>();
		for (DocumentoAluno docA : documentosAluno) {
			documentos.add(docA.getDocumento());
		}
		return documentos;
	}
	
	public List<DocumentoAluno> getDocumentosAluno() {
		return documentosAluno == null ? documentosAluno = new ArrayList<>() : documentosAluno;
	}
	
	@Override
	public boolean isNew() {
		return idAluno == null;
	}
	
	public boolean isCompleteCadastro(){
		return (
			descricao != null 		&& !descricao.equals("") &&
			matricula != null 		&& !matricula.equals("") &&
			telefone != null 		&& !telefone.equals("") &&
			cvLattes != null 		&& !cvLattes.equals("") &&
			semestreAtual != null 	&& !semestreAtual.equals("") &&
			cpf != null				&& !cpf.equals("") &&
			rg != null 				&& !rg.equals("") &&
			dataNascimento != null 	&& !dataNascimento.equals("") &&
			curso != null 			&& curso.getNome() != null && !curso.getNome().equals("") &&
			endereco != null 		&& !endereco.equals("") &&
			bairro != null 			&& !bairro.equals("") &&
			cep != null 			&& !cep.equals("") &&
			cidade != null 			&& !cidade.equals("") &&
			uf != null 				&& !uf.equals("") 
		);
	}
	
	
//## Builder
	public Aluno idAluno(Integer idAluno){
		this.idAluno = idAluno;
		return this;
	}
	
	public Aluno descricao(String descricao){
		this.descricao = descricao;
		return this;
	}
	
	public Aluno matricula(String matricula){
		this.matricula = matricula;
		return this;
	}
	
	public Aluno telefone(String telefone){
		this.telefone = telefone;
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
	
	public Aluno curso(Curso curso){
		this.curso = curso;
		return this;
	}
	
	public Aluno usuario(Usuario usuario){
		this.usuario = usuario;
		return this;
	}
	
	public Aluno endereco(String endereco){
		this.endereco = endereco;
		return this;
	}
	
	public Aluno bairro(String bairro){
		this.bairro = bairro;
		return this;
	}
	
	public Aluno cep(String cep){
		this.cep = cep;
		return this;
	}
	
	public Aluno cidade(String cidade){
		this.cidade = cidade;
		return this;
	}
	
	public Aluno uf(String uf){
		this.uf = uf;
		return this;
	}
	
	public Aluno addDocumento(DocumentoAluno docAluno) throws DAOException{
		this.getDocumentosAluno().add(docAluno.save());
		return this;
	}
	
	public Aluno vagaEstagio(VagaEstagio vagaEstagio){
		this.vagaEstagio = vagaEstagio;
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
		return dao.remove(this);
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
