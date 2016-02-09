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
 * 
 * 
 * @author edivandoalves
 *
 */
@ManagedBean
@ViewScoped
public class UsuarioBean extends BasicView<Usuario>{
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
		return instituicaos = Instituicao.findAll();
	}
	
	public String getInstituicaoNome(Integer id){
		for(Instituicao instituicao : getInstituicaos()){
			if(instituicao.getIdInstituicao().equals(id)) return instituicao.getNome();
		}
		return "";
	}
}
