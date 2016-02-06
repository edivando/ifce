package com.j7ss.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.j7ss.entity.Campus;
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
public class CampusBean extends BasicView<Campus>{
	private static final long serialVersionUID = 1L;
	
	@Override
	public Campus getEntity() {
		return entity == null ? entity = new Campus() : entity;
	}
	
	@Override
	public List<Campus> getEntitys() {
		return entitys == null ? entitys = Campus.findAll() : entitys;
	}
	
	@Override
	public void onSave() {
		Messages.showGrowlInfo("Campus", "Campus <strong>{0}</strong> salvo com sucesso!", entity.getNome());
	}
	
	@Override
	public void onRemove(Campus campus) {
		Messages.showGrowlInfo("Campus", "Campus <strong>{0}</strong> removido com sucesso!", campus.getNome());
	}
	
}
