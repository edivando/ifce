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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lombok.Setter;

import com.j7ss.entity.Aluno;
import com.j7ss.entity.constraint.AlunoStatus;
import com.j7ss.util.BasicView;
import com.j7ss.util.DAOException;
import com.j7ss.util.Messages;
import com.j7ss.view.LoginBean;

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
	
	@Setter
	@ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;
	
	@Override
	public void remove(Aluno entity) {
		Messages.showGrowlFatal("Aluno", "Ação indisponível!");
	}
	
	public void saveValido(){
		try {
			entity.status(AlunoStatus.VALIDO).save();
			Messages.showGrowlInfo("Aluno", "Aluno válido");
		} catch (DAOException e) {
			Messages.showGrowlErro("Aluno", e.getMessage());
		}
		grid();
	}
	
	public void saveInvalido(){
		try {
			entity.status(AlunoStatus.INVALIDO).save();
			Messages.showGrowlInfo("Aluno", "Aluno inválido");
		} catch (DAOException e) {
			Messages.showGrowlErro("Aluno", e.getMessage());
		}
		grid();
	}
	
	public void saveDesativar(){
		try {
			entity.getUsuario().ativo(false).save();
			Messages.showGrowlInfo("Aluno", "Aluno será desativado e não poderá usar o sistema.");
		} catch (DAOException e) {
			Messages.showGrowlErro("Aluno", e.getMessage());
		}
		grid();
	}
	
//******************************************************************************************************************************
//## Getters Setters
	@Override
	public Aluno getEntity() {
		return entity == null ? entity = new Aluno() : entity;
	}
	
	@Override
	public List<Aluno> getEntitys() {
		return entitys == null ? entitys = Aluno.findByInstituicaoNotStatus(loginBean.getUsuario().getInstituicao(), AlunoStatus.NOVO) : entitys;
	}
}
