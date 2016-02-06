package com.j7ss.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.j7ss.entity.Usuario;
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
public class UsuarioBean extends BasicView<Usuario>{
	private static final long serialVersionUID = 1L;
	
	@Override
	public Usuario getEntity() {
		return entity == null ? entity = new Usuario() : entity;
	}
	
	@Override
	public List<Usuario> getEntitys() {
		return entitys == null ? entitys = Usuario.findAll() : entitys;
	}
	
	@Override
	public void onSave() {
		Messages.showGrowlInfo("Usuario", "Usuario <strong>{0}</strong> salvo com sucesso!", entity.getNome());
	}
	
	@Override
	public void onRemove(Usuario usuario) {
		Messages.showGrowlInfo("Usuario", "Usuario <strong>{0}</strong> removido com sucesso!", usuario.getNome());
	}
}
