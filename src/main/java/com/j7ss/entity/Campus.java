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
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
@Table(name = "campus")
@EqualsAndHashCode
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

	@Getter @Setter
	private String endereco;
	@Getter @Setter
	private String numero;
	@Getter @Setter
	private String bairro;
	@Getter @Setter
	private String cep;
	@Getter @Setter
	private String uf;
	@Getter @Setter
	private String cidade;
	
	@ManyToOne
	@Getter @Setter
	private Instituicao instituicao = new Instituicao();
	
	@OneToMany(mappedBy="campus", fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
	@Getter @Setter
	private List<Departamento> departamentos;
	
	@Override
	public String toString() {
		return nome;
	}
	
	
//******************************************************************************************************************************
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
	
	public Campus instituicao(Instituicao instituicao){
		this.instituicao = instituicao;
		return this;
	}
	
	public Campus addDepartamento(Departamento departamento){
		if(departamentos == null){
			departamentos = new ArrayList<>();
		}
		departamentos.add(departamento);
		return this;
	}
	
	public Campus removeDepartamento(Departamento departamento){
		if(departamentos != null){
			departamentos.remove(departamento);
		}
		return this;
	}
	
	public Campus endereco(String endereco){
		this.endereco = endereco;
		return this;
	}
	
	public Campus bairro(String bairro){
		this.bairro = bairro;
		return this;
	}
	
	public Campus cep(String cep){
		this.cep = cep;
		return this;
	}
	
	public Campus cidade(String cidade){
		this.cidade = cidade;
		return this;
	}
	
	public Campus uf(String uf){
		this.uf = uf;
		return this;
	}
	
	
//******************************************************************************************************************************
//## Getters Setters
	@Override
	public boolean isNew() {
		return idCampus == null;
	}
	
	
//******************************************************************************************************************************
//## DAO
	private static DAO<Campus> dao = new DAO<Campus>(Campus.class);
	
	@Override
	public Campus save() throws DAOException{
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(this);
	}

	public static List<Campus> findByNomeLike(Instituicao instituicao, String nome){
		return dao.findByQuery("SELECT i FROM Campus i WHERE i.instituicao = ?1 AND lower(i.nome) like ?2" ,instituicao, "%"+nome.toLowerCase()+"%");
	}
}
