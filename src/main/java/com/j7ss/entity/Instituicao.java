package com.j7ss.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.j7ss.util.DAO;
import com.j7ss.util.DAOException;
import com.j7ss.util.IGenericEntity;

import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
public class Instituicao implements IGenericEntity<Instituicao>{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer idInstituicao;
	@Getter @Setter
	private String nome;
	@Getter @Setter
	private String email;
	@Getter @Setter
	private String telefone;
	@Getter @Setter
	private String responsavel;
	
	@OneToMany(mappedBy="instituicao", fetch=FetchType.EAGER)
	@Getter @Setter
	private List<Campus> campus;
	
	@Override
	public String toString() {
		return nome;
	}
	
	@Override
	public boolean isNew() {
		return idInstituicao == null;
	}
	
	
//## Builder
	public Instituicao idInstituicao(Integer idInstituicao){
		this.idInstituicao = idInstituicao;
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
	
	public Instituicao addCampus(Campus campu){
		if(campus == null){
			campus = new  ArrayList<>();
		}
		campus.add(campu);
		return this;
	}
	
	public Instituicao removeCampus(Campus campu){
		if(campus != null){
			campus.remove(campu);
		}
		return this;
	}
	
//## DAO
	private static DAO<Instituicao> dao = new DAO<Instituicao>(Instituicao.class);
	
	@Override
	public Instituicao save() throws DAOException{
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(this);
	}
	
	public static List<Instituicao> findAll(){
		return dao.findAll();
	}
	
	public static Instituicao findById(Integer idInstituicao){
		return dao.findOne(idInstituicao);
	}
	
	public static Long countAll(){
		return dao.countAll();
	}
	
	public static List<Instituicao> findByNomeLike(String nome){
		return dao.findByQuery("SELECT i FROM Instituicao i WHERE lower(i.nome) like ?1" , "%"+nome.toLowerCase()+"%");
	}
	
	public static List<Instituicao> findByNome(String nome){
		return dao.findByQuery("SELECT i FROM Instituicao i WHERE i.nome = ?1" , nome);
	}
	
	public static Instituicao findByIdUsuario(Integer idUsuario){
		return dao.findOneByQuery("SELECT i FROM Instituicao i WHERE i.idUsuario = ?1" , idUsuario);
	}
}
