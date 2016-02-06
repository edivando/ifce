package com.j7ss.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "campus")
public class Campus implements IGenericEntity<Campus>{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer idCampus;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String telefone;
	
	@Getter @Setter
	private String email;
	
	@ManyToOne
	@Getter @Setter
	private Instituicao instituicao;
	
//## Builder
	public Campus idCampus(Integer idCampus){
		this.idCampus = idCampus;
		return this;
	}
	
	public Campus nome(String nome){
		this.nome = nome;
		return this;
	}
	
	public Campus telefone(String telefone){
		this.telefone = telefone;
		return this;
	}
	
	public Campus email(String email){
		this.email = email;
		return this;
	}
	
//## DAO
	private static DAO<Campus> dao = new DAO<Campus>(Campus.class);
	
	@Override
	public Campus save() throws DAOException{
		return idCampus == null ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(idCampus);
	}
	
	public static List<Campus> findAll(){
		return dao.findAll();
	}
	
	public static Long countAll(){
		return dao.countAll();
	}
}
