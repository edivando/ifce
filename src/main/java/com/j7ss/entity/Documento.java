/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "documento")
@EqualsAndHashCode
public class Documento implements IGenericEntity<Documento> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer idDocumento;
	@Getter @Setter
	private String nome;
	@Getter @Setter
	private String pagina;
	@Getter @Setter
	private String titulo;
	@Column(columnDefinition="text")
	@Getter @Setter
	private String descricao;
	@Column(columnDefinition="text")
	@Getter @Setter
	private String htmlPage;
	@Getter @Setter
	private Boolean obrigatorio = true;
	
	@Override
	public String toString() {
		return nome;
	}
	
	@Override
	public boolean isNew() {
		return idDocumento == null;
	}
	
//## Builder
	public Documento idDocumento(Integer idDocumento){
		this.idDocumento = idDocumento;
		return this;
	}
	
	public Documento nome(String nome){
		this.nome = nome;
		return this;
	}
	
	public Documento pagina(String pagina){
		this.pagina = pagina;
		return this;
	}
	
	public Documento titulo(String titulo){
		this.titulo = titulo;
		return this;
	}
	
	public Documento descricao(String descricao){
		this.descricao = descricao;
		return this;
	}
	
	public Documento obrigatorio(Boolean obrigatorio){
		this.obrigatorio = obrigatorio;
		return this;
	}
	
	public Documento htmlPage(String htmlPage){
		this.htmlPage = htmlPage;
		return this;
	}
	
	
//## DAO
	private static DAO<Documento> dao = new DAO<Documento>(Documento.class);
	
	@Override
	public Documento save() throws DAOException{
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(this);
	}
	
	public static List<Documento> findAll(){
		return dao.findAll();
	}
	
	public static Long countAll(){
		return dao.countAll();
	}
}