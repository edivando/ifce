package com.j7ss.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	
	// Endereco
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
	
	@ManyToOne(fetch=FetchType.EAGER)
	@Getter @Setter
	private Instituicao instituicao = new Instituicao();
	
	@OneToMany(mappedBy="campus", fetch=FetchType.EAGER)
	@Getter @Setter
	private List<Departamento> departamentos;
	
	@Override
	public String toString() {
		return nome;
	}
	
	@Override
	public boolean isNew() {
		return idCampus == null;
	}
	
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
	
	public static List<Campus> findAll(){
		return dao.findAll();
	}
	
	public static Long countAll(){
		return dao.countAll();
	}
	
	public static List<Campus> findByInstituicao(Instituicao instituicao){
		return dao.findByQuery("Select c From Campus c Where c.instituicao = ?1 ", instituicao);
	}
}
