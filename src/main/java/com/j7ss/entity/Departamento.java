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
@Table(name = "departamento")
public class Departamento implements IGenericEntity<Departamento> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer idDepartamento;
	
	@Getter @Setter
	private String nome;
	
	@ManyToOne
	@Getter @Setter
	private Campus campus;
	
//## Builder
	public Departamento nome(Integer idDepartamento){
		this.idDepartamento = idDepartamento;
		return this;
	}
	
	public Departamento nome(String nome){
		this.nome = nome;
		return this;
	}
	
//## DAO
	private static DAO<Departamento> dao = new DAO<Departamento>(Departamento.class);
	
	@Override
	public Departamento save() throws DAOException{
		return idDepartamento == null ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(idDepartamento);
	}
	
	public static List<Departamento> findAll(){
		return dao.findAll();
	}
	
	public static Long countAll(){
		return dao.countAll();
	}
}
