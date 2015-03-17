package br.edu.ifce.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import lombok.Setter;
import br.edu.ifce.dao.UsuarioDAO;
import br.edu.ifce.entity.TipoUsuario;
import br.edu.ifce.entity.Usuario;
import br.edu.ifce.util.MD5;
import br.edu.ifce.util.exception.DAOException;

/**
 * 
 * 
 * 
 * @author Edivando Alves
 *
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Setter
	private Usuario usuario;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
//	public void loginTwo(){
//		try {
//			List<Usuario> usuarios = usuarioDAO.ListByCdEmailAndSenha(usuario.getCdEmail(), MD5.md5(usuario.getCdSenha()));
//			if(usuarios != null && usuarios.size() > 0 ){
//				usuario = usuarios.get(0);
//				if(usuario.getTipoUsuario().getId() == 2){
//					empresa = empresaDAO.find(usuario.getEmpresa().getId());
//				}else if(usuario.getTipoUsuario().getId() == 3){
//					aluno = alunoDao.find(1); // TODO: Obter id do aluno nao do usuario
//				}
//				FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
//			}else{
//				usuario = new Usuario();
//				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage(FacesMessage.SEVERITY_WARN, "Desculpe!", "Mas o email ou a senha informada n�o confere. Tente novamente!")));
//			}
//		} catch (Exception e) {
//		
//		}
//	}
	
	
	public void login(){
		try {
			List<Usuario> usuarios = usuarioDAO.findByEmailAndSenha(usuario.getEmail(), MD5.md5(usuario.getSenha()));
			if(usuarios != null && usuarios.size() > 0 ){
				usuario = usuarios.get(0);
				FacesContext.getCurrentInstance().getExternalContext().redirect("home.html");
			}else{
				usuario = new Usuario();
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage(FacesMessage.SEVERITY_WARN, "Desculpe!", "Mas o email ou a senha informada não confere. Tente novamente!")));
			}
		} catch (Exception e) {
		
		}
	}
	
	public void logoff(){
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.html");
		} catch (IOException e) {
			
		}
	}
	
	public String beginRegister(){
		try {
			usuario.setSenha(MD5.md5(usuario.getSenha()));
			usuario.setEmailValido(false);
			usuario.setAtivo(true);
			usuarioDAO.add(usuario);
			usuario = null;
			return "login";
		} catch (DAOException e) {
			return "cadastro";
		}
	}
	
	public Usuario getUsuario() {
		return usuario == null ? usuario = new Usuario() : usuario;
	}

	public boolean isPagePermission(String paginaDestino) {
		if(usuario != null && usuario.getTipoUsuario() != null){
			for (String page : usuario.getTipoUsuario().getPages()) {
				if(page.equals(paginaDestino)) return true;
			}
		}
		return false;
	}
	
	public boolean isAluno(){
		return usuario.getTipoUsuario().equals(TipoUsuario.ALUNO.name());
	}
	
	public boolean isEmpresa(){
		return usuario.getTipoUsuario().equals(TipoUsuario.EMPRESA.name());
	}
}
