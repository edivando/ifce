package com.j7ss.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.j7ss.entity.Instituicao;
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
public class InstituicaoBean extends BasicView<Instituicao>{
	private static final long serialVersionUID = 1L;
	
	@Override
	public Instituicao getEntity() {
		return entity == null ? entity = new Instituicao() : entity;
	}
	
	@Override
	public List<Instituicao> getEntitys() {
		return entitys == null ? entitys = Instituicao.findAll() : entitys;
	}
	
	@Override
	public void onSave() {
		Messages.showGrowlInfo("Instituicao", "Instituicao <strong>{0}</strong> salvo com sucesso!", entity.getNome());
	}
	
	@Override
	public void onRemove(Instituicao instituicao) {
		Messages.showGrowlInfo("Instituicao", "Instituicao <strong>{0}</strong> removido com sucesso!", instituicao.getNome());
	}

}
