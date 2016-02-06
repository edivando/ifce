package com.j7ss.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "instituicao")
public class Instituicao implements IGenericEntity<Instituicao>{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer idInstiruicao;
	@Getter @Setter
	private String nome;
	@Getter @Setter
	private String email;
	@Getter @Setter
	private String telefone;
	@Getter @Setter
	private String responsavel;
	
	@OneToMany(mappedBy="instituicao")
	@Getter @Setter
	private List<Campus> campus;
	
	
//## Builder
	public Instituicao idInstiruicao(Integer idInstiruicao){
		this.idInstiruicao = idInstiruicao;
		return this;
	}
	
	public Instituicao nome(String nome){
		this.nome = nome;
		return this;
	}
	
	public Instituicao email(String email){
		this.email = email;
		return this;
	}
	
	public Instituicao telefone(String telefone){
		this.telefone = telefone;
		return this;
	}
	
	public Instituicao responsavel(String responsavel){
		this.responsavel = responsavel;
		return this;
	}
	
//## DAO
	private static DAO<Instituicao> dao = new DAO<Instituicao>(Instituicao.class);
	
	@Override
	public Instituicao save() throws DAOException{
		return idInstiruicao == null ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(idInstiruicao);
	}
	
	public static List<Instituicao> findAll(){
		return dao.findAll();
	}
	
	public static Long countAll(){
		return dao.countAll();
	}
	
	public static Instituicao findByIdUsuario(Integer idUsuario){
		return dao.findOneByQuery("SELECT i FROM Instituicao i WHERE i.idUsuario = ?1" , idUsuario);
	}
}
