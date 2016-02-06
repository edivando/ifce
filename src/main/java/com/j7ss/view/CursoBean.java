package com.j7ss.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.j7ss.entity.Curso;
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
public class CursoBean extends BasicView<Curso>{
	private static final long serialVersionUID = 1L;
	
	@Override
	public Curso getEntity() {
		return entity == null ? entity = new Curso() : entity;
	}
	
	@Override
	public List<Curso> getEntitys() {
		return entitys == null ? entitys = Curso.findAll() : entitys;
	}
	
	@Override
	public void onSave() {
		Messages.showGrowlInfo("Curso", "Curso <strong>{0}</strong> salvo com sucesso!", entity.getNome());
	}
	
	@Override
	public void onRemove(Curso curso) {
		Messages.showGrowlInfo("Curso", "Curso <strong>{0}</strong> removido com sucesso!", curso.getNome());
	}
	
}
