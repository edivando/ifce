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

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lombok.Setter;

import org.primefaces.event.FlowEvent;

import com.j7ss.entity.Aluno;
import com.j7ss.entity.Usuario;
import com.j7ss.util.WebContext;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class AlunoCompleteCadastroBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Setter
	@ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;
	
	public Aluno getAluno(){
		return loginBean.getUsuario().getAluno();
	}
	
	public Usuario getUsuario(){
		return loginBean.getUsuario();
	}
	
	public void save(){
		try {
			loginBean.getUsuario().save();
			loginBean.getUsuario().getAluno().usuario(loginBean.getUsuario()).save();
			WebContext.redirect("alunoHome.html");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }

}
