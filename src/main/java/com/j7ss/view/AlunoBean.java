package com.j7ss.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.j7ss.entity.Aluno;
import com.j7ss.util.BasicView;
import com.j7ss.util.Messages;

/**
 * 
 * 
 * 
 * @author edivandoalves
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
	
}
