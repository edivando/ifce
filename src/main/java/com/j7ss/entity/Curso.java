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
@Table(name = "curso")
@EqualsAndHashCode
public class Curso implements IGenericEntity<Curso>{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer idCurso;
	@Getter @Setter
	private String nome;
	
	@ManyToOne
	@Getter @Setter
	private Departamento departamento;
	
	@Override
	public String toString() {
		return nome;
	}
	
	@Override
	public boolean isNew() {
		return idCurso == null;
	}
	
//## Builder
	public Curso idCurso(Integer idCurso){
		this.idCurso = idCurso;
		return this;
	}
	
	public Curso nome(String nome){
		this.nome = nome;
		return this;
	}
	
	public Curso departamento(Departamento departamento){
		this.departamento = departamento;
		return this;
	}
	
//## DAO
	private static DAO<Curso> dao = new DAO<Curso>(Curso.class);
	
	@Override
	public Curso save() throws DAOException{
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(this);
	}
	
	public static List<Curso> findAll(){
		return dao.findAll();
	}
	
	public static Long countAll(){
		return dao.countAll();
	}
}
