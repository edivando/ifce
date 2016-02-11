/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.j7ss.entity.VagaEstagio;
import com.j7ss.util.BasicView;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class VagaEstagioBean extends BasicView<VagaEstagio>{
	private static final long serialVersionUID = 1L;
	
	@Override
	public VagaEstagio getEntity() {
		return entity == null ? entity = new VagaEstagio() : entity;
	}
	
	@Override
	public List<VagaEstagio> getEntitys() {
		return entitys == null ? entitys = VagaEstagio.findAll() : entitys;
	}
}
