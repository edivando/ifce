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

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
	
	@OneToMany(mappedBy="curso", fetch=FetchType.EAGER)
	@OrderBy("ordem")
	@Getter @Setter
	private List<DocumentoCurso> documentoCursos;
	
	@Override
	public String toString() {
		return nome;
	}
	
	@Override
	public boolean isNew() {
		return idCurso == null;
	}
	
	public List<Documento> getDocumentos(){
		List<Documento> documentos = new ArrayList<>();
		for (DocumentoCurso dc : documentoCursos) {
			documentos.add(dc.getDocumento());
		}
		return documentos;
	}
	
	public void setDocumentos(List<Documento> documentos) throws DAOException{
		if(documentoCursos != null){
			for (DocumentoCurso dcs : documentoCursos) {
				dcs.remove();
			}
		}
		documentoCursos = new ArrayList<>();
		for (int i = 0; i < documentos.size(); i++) {
			documentoCursos.add( new DocumentoCurso(this).documento(documentos.get(i)).ordem(i).save() );
		}
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
	
	public Curso documentoCursos(List<DocumentoCurso> documentoCursos){
		this.documentoCursos = documentoCursos;
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
	
	public static Curso findById(Integer idCurso){
		return dao.findOne(idCurso);
	}
	
	public static Long countAll(){
		return dao.countAll();
	}
	
	public static List<Curso> findByNomeLike(Departamento departamento, String nome){
		return dao.findByQuery("SELECT i FROM Curso i WHERE i.departamento = ?1 AND lower(i.nome) like ?2" ,departamento, "%"+nome.toLowerCase()+"%");
	}
}
