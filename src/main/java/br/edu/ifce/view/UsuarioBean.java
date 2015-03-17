package br.edu.ifce.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;
import br.edu.ifce.dao.UsuarioDAO;
import br.edu.ifce.entity.Usuario;
import br.edu.ifce.util.exception.DAOException;

/**
 * 
 * 
 * 
 * @author edivandoalves
 *
 */
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Setter
	private Usuario usuario;
	private List<Usuario> usuarios;
	@Getter
	private boolean form = false;
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	public void grid(){
		usuario  = null;
		usuarios = null;
		form = false;
	}
	
	public void form(){
		form = true;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios == null ? usuarios = usuarioDAO.findAll() : usuarios;
	}
	
	public void salvar(){
		try {
			usuarioDAO.save(usuario);
			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Usuário", "salvo com sucesso!")));
		} catch (DAOException e) {
			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro durante o processo de salvar os dados")));
		}
		grid();
	}
	
	public void update(Usuario usuario){
		try {
			usuarioDAO.save(usuario);
			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Usuário", "salvo com sucesso!")));
		} catch (DAOException e) {
			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro durante o processo de salvar os dados")));
		}
	}
	
	public void excluir(){
		if(usuario != null){
			try {
				usuarioDAO.remove(usuario);
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Usuário", "Excluído com sucesso!")));
			} catch (DAOException e) {
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro ao excluir")));
			}
		}
		grid();
	}

	public Usuario getUsuario() {
		return usuario == null ? usuario = new Usuario() : usuario;
	}	
}
