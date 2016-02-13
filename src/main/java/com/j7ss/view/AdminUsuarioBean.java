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

import com.j7ss.entity.Instituicao;
import com.j7ss.entity.TipoUsuario;
import com.j7ss.entity.Usuario;
import com.j7ss.util.BasicView;
import com.j7ss.util.MD5;
import com.j7ss.util.Messages;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class AdminUsuarioBean extends BasicView<Usuario>{
	private static final long serialVersionUID = 1L;
	
	private List<Instituicao> instituicaos;
	
	@Override
	public Usuario getEntity() {
		return entity == null ? entity = new Usuario() : entity;
	}
	
	@Override
	public List<Usuario> getEntitys() {
		return entitys == null ? entitys = Usuario.findAllMinusAluno() : entitys;
	}
	
	@Override
	public void onSave() {
		Messages.showGrowlInfo("Usuario", "Usuario <strong>{0}</strong> salvo com sucesso!", entity.getNome());
	}
	
	@Override
	public void onRemove(Usuario usuario) {
		Messages.showGrowlInfo("Usuario", "Usuario <strong>{0}</strong> removido com sucesso!", usuario.getNome());
	}
	
	@Override
	public void save() {
		if(entity.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR)){
			entity.setIdInstituicao(null);
		}
		entity.senha(MD5.md5(entity.getSenha()));
		super.save();
	}
	
	public void save(Usuario usuario) {
		if(usuario.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR)){
			usuario.setIdInstituicao(null);
		}
		entity = usuario;
		super.save();
	}
	
	public List<Instituicao> getInstituicaos() {
		return instituicaos == null ? Instituicao.findAll() : instituicaos;
	}
	
	public String getInstituicaoNome(Integer id){
		for(Instituicao instituicao : getInstituicaos()){
			if(instituicao.getIdInstituicao().equals(id)) return instituicao.getNome();
		}
		return "";
	}
}
