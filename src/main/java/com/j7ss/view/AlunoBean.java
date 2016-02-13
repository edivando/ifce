/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.view;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.j7ss.entity.Aluno;
import com.j7ss.entity.Documento;
import com.j7ss.util.BasicView;
import com.j7ss.util.Messages;
import com.j7ss.util.WebContext;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class AlunoBean extends BasicView<Aluno>{
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
	public void onSave() {
		Messages.showGrowlInfo("Aluno", "Aluno salvo com sucesso!");
	}
	
	@Override
	public void onRemove(Aluno aluno) {
		Messages.showGrowlInfo("Aluno", "Aluno removido com sucesso!");
	}
	
	public void openDocumento(Documento doc){
		WebContext.setFlash("DocFichaMatricula", doc);
		try {
			WebContext.redirect("docFichaMatricula.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
