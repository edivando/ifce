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
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "vaga_estagio_atividade_diaria")
public class VagaEstagioAtividadeDiaria implements IGenericEntity<VagaEstagioAtividadeDiaria>{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer idAtividade;
	
	@Getter @Setter
	private Date data;
	
	@Getter @Setter
	private String descricao;
	
	@Getter @Setter
	private Integer quantidadeHoras;
	
	@Override
	public boolean isNew() {
		return idAtividade == null;
	}
	
//## Builder
	public VagaEstagioAtividadeDiaria idAtividade(Integer idAtividade){
		this.idAtividade = idAtividade;
		return this;
	}
	
	public VagaEstagioAtividadeDiaria data(Date data){
		this.data = data;
		return this;
	}
	
	public VagaEstagioAtividadeDiaria descricao(String descricao){
		this.descricao = descricao;
		return this;
	}
	
	public VagaEstagioAtividadeDiaria quantidadeHoras(Integer quantidadeHoras){
		this.quantidadeHoras = quantidadeHoras;
		return this;
	}
	
//## DAO
	private static DAO<VagaEstagioAtividadeDiaria> dao = new DAO<VagaEstagioAtividadeDiaria>(VagaEstagioAtividadeDiaria.class);
	
	@Override
	public VagaEstagioAtividadeDiaria save() throws DAOException{
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(this);
	}
	
	public static List<VagaEstagioAtividadeDiaria> findAll(){
		return dao.findAll();
	}
	
	public static Long countAll(){
		return dao.countAll();
	}
}
