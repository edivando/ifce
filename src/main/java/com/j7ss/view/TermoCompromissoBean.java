/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lombok.Getter;
import lombok.Setter;

import com.j7ss.entity.Aluno;
import com.j7ss.entity.Empresa;
import com.j7ss.entity.Usuario;
import com.j7ss.entity.VagaEstagio;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class TermoCompromissoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
	private Empresa empresa = new Empresa();
	private List<Empresa> empresas;
	
	@Setter
	private VagaEstagio vagaEstagio = new VagaEstagio();
	
	@Setter
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	
	public Aluno getAluno(){
		return loginBean.getAluno();
	}
	
	public Usuario getUsuario(){
		return loginBean.getUsuario();
	}
	
	public VagaEstagio getVagaEstagio() {
		return vagaEstagio == null ? vagaEstagio = new VagaEstagio() : vagaEstagio;
	}
	
	public List<Empresa> getEmpresas(){
		return empresas == null ? empresas = Empresa.findAll() : empresas;
	}
	

}
