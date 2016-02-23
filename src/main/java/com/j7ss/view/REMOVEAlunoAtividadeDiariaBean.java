/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lombok.Setter;

import com.j7ss.entity.VagaEstagioAtividadeDiaria;
import com.j7ss.util.BasicView;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class REMOVEAlunoAtividadeDiariaBean extends BasicView<VagaEstagioAtividadeDiaria>{
	private static final long serialVersionUID = 1L;
	
	@Setter
	@ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;
	
//	@Override
//	public VagaEstagioAtividadeDiaria getEntity() {
//		if(entity == null){
//			entity = new VagaEstagioAtividadeDiaria();
//			if(loginBean != null && loginBean.getUsuario() != null && loginBean.getUsuario().getAluno() != null){
//				entity.setVagaEstagio(loginBean.getUsuario().getAluno().getVagaEstagio());
//			}
//		}
//		return entity;
//	}
//	
//	@Override
//	public List<VagaEstagioAtividadeDiaria> getEntitys() {
//		if(loginBean == null || loginBean.getUsuario() == null || loginBean.getUsuario().getAluno() == null || loginBean.getUsuario().getAluno().getVagaEstagio() == null){
//			return new ArrayList<VagaEstagioAtividadeDiaria>();
//		}
//		return entitys == null ? entitys = VagaEstagioAtividadeDiaria.findByVagaEstagio(loginBean.getUsuario().getAluno().getVagaEstagio()) : entitys;
//	}
	
}
