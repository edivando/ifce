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
@Table(name = "documentoAluno")
@EqualsAndHashCode @ToString
public class DocumentoAluno implements IGenericEntity<DocumentoAluno> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer idDocumentoAluno;
	@Getter @Setter
	private Integer ordem;
	@Getter @Setter
	private DocumentoStatus status;

	@ManyToOne
	@Getter @Setter
	private Aluno aluno = new Aluno();
	
	@ManyToOne
	@Getter @Setter
	private Documento documento = new Documento();
	
	@OneToMany(mappedBy="documentoAluno", fetch=FetchType.EAGER)
	@OrderBy("date")
	@Setter
	private List<DocumentoAlunoMessage> documentoAlunoMessages;
	
	
	public String getLinkPagina(){
		if(status == DocumentoStatus.INDISPONIVEL){
			return "";
		}
		return documento.getPagina();
	}
	
	@Override
	public boolean isNew() {
		return idDocumentoAluno == null;
	}
	
	public DocumentoAluno() { }
	
	public DocumentoAluno(Aluno aluno){
		this.aluno = aluno;
	}

	
//## Builder
	public DocumentoAluno idDocumentoAluno(Integer idDocumentoAluno){
		this.idDocumentoAluno = idDocumentoAluno;
		return this;
	}
	
	public DocumentoAluno ordem(Integer ordem){
		this.ordem = ordem;
		return this;
	}
	
	public DocumentoAluno status(DocumentoStatus status){
		this.status = status;
		return this;
	}
	
	public DocumentoAluno aluno(Aluno aluno){
		this.aluno = aluno;
		return this;
	}
	
	public DocumentoAluno documento(Documento documento){
		this.documento = documento;
		return this;
	}

	
//## DAO
	private static DAO<DocumentoAluno> dao = new DAO<DocumentoAluno>(DocumentoAluno.class);
	
	@Override
	public DocumentoAluno save() throws DAOException{
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(this);
	}
	
	public static List<DocumentoAluno> findAll(){
		return dao.findAll();
	}
	
	public static Long countAll(){
		return dao.countAll();
	}
}