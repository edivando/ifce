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
@Table(name = "departamento")
@EqualsAndHashCode
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
	
	@OneToMany(mappedBy="departamento", fetch=FetchType.EAGER)
	@Getter @Setter
	private List<Curso> cursos;
	
	@Override
	public String toString() {
		return nome;
	}
	
	@Override
	public boolean isNew() {
		return idDepartamento == null;
	}
	
//## Builder
	public Departamento nome(Integer idDepartamento){
		this.idDepartamento = idDepartamento;
		return this;
	}
	
	public Departamento nome(String nome){
		this.nome = nome;
		return this;
	}
	
	public Departamento campus(Campus campus){
		this.campus = campus;
		return this;
	}
	
	public Departamento addCurso(Curso curso){
		if(cursos == null){
			cursos = new ArrayList<>();
		}
		this.cursos.add(curso);
		return this;
	}
	
	public Departamento removeCurso(Curso curso){
		if(cursos != null){
			this.cursos.remove(curso);
		}
		return this;
	}
	
//## DAO
	private static DAO<Departamento> dao = new DAO<Departamento>(Departamento.class);
	
	@Override
	public Departamento save() throws DAOException{
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(this);
	}
	
	public static List<Departamento> findAll(){
		return dao.findAll();
	}
	
	public static Long countAll(){
		return dao.countAll();
	}
}
