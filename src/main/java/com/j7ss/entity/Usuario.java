package com.j7ss.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.j7ss.util.DAO;
import com.j7ss.util.DAOException;
import com.j7ss.util.IGenericEntity;

import lombok.Getter;
import lombok.Setter;

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
	private TipoUsuario tipoUsuario;
	@Getter @Setter
	private Boolean emailValido = false;
	@Getter @Setter
	private Boolean ativo = true;
	
	@Getter @Setter
	private Integer idInstituicao;
	
	@Override
	public boolean isNew() {
		return idUsuario == null;
	}
	
//## Builder
	public Usuario idUsuario(Integer idUsuario){
		this.idUsuario = idUsuario;
		return this;
	}
	
	public Usuario nome(String nome){
		this.nome = nome;
		return this;
	}
	
	public Usuario email(String email){
		this.email = email;
		return this;
	}
	
	public Usuario senha(String senha){
		this.senha = senha;
		return this;
	}
	
	public Usuario tipoUsuario(TipoUsuario tipoUsuario){
		this.tipoUsuario = tipoUsuario;
		return this;
	}
	
	public Usuario emailValido(Boolean emailValido){
		this.emailValido = emailValido;
		return this;
	}
	
	public Usuario ativo(Boolean ativo){
		this.ativo = ativo;
		return this;
	}
	
//## DAO
	private static DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
	
	@Override
	public Usuario save() throws DAOException{
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(this);
	}
	
	public static List<Usuario> findAll(){
		return dao.findAll();
	}
	
	public static Long countAll(){
		return dao.countAll();
	}
	
	public static List<Usuario> findAllMinusAluno(){
		return dao.findByQuery("SELECT u FROM Usuario u WHERE u.tipoUsuario != ?1", TipoUsuario.ALUNO);
	}
	
	public static List<Usuario> findByEmailAndSenha(String email, String senha){
		return dao.findByQuery("SELECT u FROM Usuario u WHERE u.email = ?1 and u.senha = ?2", email, senha);
	}
	
}
