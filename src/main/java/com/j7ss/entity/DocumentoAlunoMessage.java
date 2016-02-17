/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@Table(name = "documentoAlunoMessage")
@EqualsAndHashCode @ToString
public class DocumentoAlunoMessage implements IGenericEntity<DocumentoAlunoMessage> {

	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer idDocumentoAlunoMessage;
	@Getter @Setter
	private String message;
	@Getter @Setter
	private Date date;
	
	@ManyToOne
	@Getter @Setter
	private Usuario usuario;

	@ManyToOne
	@Getter @Setter
	private DocumentoAluno documentoAluno = new DocumentoAluno();
		

//******************************************************************************************************************************		
//## Builder
	public DocumentoAlunoMessage idDocumentoAlunoMessage(Integer idDocumentoAlunoMessage){
		this.idDocumentoAlunoMessage = idDocumentoAlunoMessage;
		return this;
	}
	
	public DocumentoAlunoMessage message(String message){
		this.message = message;
		return this;
	}
	
	public DocumentoAlunoMessage date(Date date){
		this.date = date;
		return this;
	}
	
	public DocumentoAlunoMessage documentoAluno(DocumentoAluno documentoAluno){
		this.documentoAluno = documentoAluno;
		return this;
	}
	
	
//******************************************************************************************************************************
//## Getters Setters
	@Override
	public boolean isNew() {
		return idDocumentoAlunoMessage == null;
	}
	

//******************************************************************************************************************************		
//## DAO
	private static DAO<DocumentoAlunoMessage> dao = new DAO<DocumentoAlunoMessage>(DocumentoAlunoMessage.class);
	
	@Override
	public DocumentoAlunoMessage save() throws DAOException{
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(this);
	}
	
}