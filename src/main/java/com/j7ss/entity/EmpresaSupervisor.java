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
@Table(name = "empresa_supervisor")
@EqualsAndHashCode @ToString
public class EmpresaSupervisor implements IGenericEntity<EmpresaSupervisor> {
	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer idEmpresaSupervisor;
	@Getter @Setter
	private String supervisor;
	@Getter @Setter
	private String cargoSupervisor;
	@Getter @Setter
	private String telefoneSupervisor;
		
	@ManyToOne
	@Setter
	private Empresa empresa;
		
	@Override
	public boolean isNew() {
		return idEmpresaSupervisor == null;
	}
		

		
//## Builder
	public EmpresaSupervisor idEmpresaSupervisor(Integer idEmpresaSupervisor){
		this.idEmpresaSupervisor = idEmpresaSupervisor;
		return this;
	}
	
	public EmpresaSupervisor supervisor(String supervisor){
		this.supervisor = supervisor;
		return this;
	}
	
	public EmpresaSupervisor cargoSupervisor(String cargoSupervisor){
		this.cargoSupervisor = cargoSupervisor;
		return this;
	}
	
	public EmpresaSupervisor telefoneSupervisor(String telefoneSupervisor){
		this.telefoneSupervisor = telefoneSupervisor;
		return this;
	}
	
	
//## DAO
	private static DAO<EmpresaSupervisor> dao = new DAO<EmpresaSupervisor>(EmpresaSupervisor.class);
	
	@Override
	public EmpresaSupervisor save() throws DAOException{
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(this);
	}
	
	public static List<EmpresaSupervisor> findAll(){
		return dao.findAll();
	}
	
	public static Long countAll(){
		return dao.countAll();
	}
}
