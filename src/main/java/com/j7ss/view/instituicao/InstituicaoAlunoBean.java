/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.view.instituicao;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.j7ss.entity.Aluno;
import com.j7ss.entity.constraint.AlunoStatus;
import com.j7ss.util.BasicView;
import com.j7ss.util.DAOException;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class InstituicaoAlunoBean extends BasicView<Aluno>{
	private static final long serialVersionUID = 1L;
	
	@Override
	public Aluno getEntity() {
		return entity == null ? entity = new Aluno() : entity;
	}
	
	@Override
	public List<Aluno> getEntitys() {
		return entitys == null ? entitys = Aluno.findAll() : entitys;
	}
	
	@Override
	public void remove(Aluno entity) {
		// Nao pode remover aluno
	}
	
	public void saveValido(){
		try {
			entity.status(AlunoStatus.VALIDO).save();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		grid();
	}
	
	public void saveInvalido(){
		try {
			entity.status(AlunoStatus.INVALIDO).save();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		grid();
	}
	
	public void saveDesativar(){
		try {
			entity.getUsuario().ativo(false).save();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		grid();
	}

}
