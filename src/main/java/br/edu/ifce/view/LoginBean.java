package br.edu.ifce.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import lombok.Getter;
import lombok.Setter;
import br.edu.ifce.dao.AlunoDAO;
import br.edu.ifce.dao.EmpresaDAO;
import br.edu.ifce.dao.InstituicaoDAO;
import br.edu.ifce.dao.UsuarioDAO;
import br.edu.ifce.entity.Aluno;
import br.edu.ifce.entity.Empresa;
import br.edu.ifce.entity.Instituicao;
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
	@Getter
	private Aluno aluno;
	@Getter
	private Instituicao instituicao;
	@Getter
	private Empresa empresa;
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private EmpresaDAO empresaDAO = new EmpresaDAO();
	private InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
	private AlunoDAO alunoDAO = new AlunoDAO();
	
	public void login(){	
		try {
			List<Usuario> usuarios = usuarioDAO.findByEmailAndSenha(usuario.getEmail(), MD5.md5(usuario.getSenha()));
			if(usuarios != null && usuarios.size() > 0 ){
				usuario = usuarios.get(0);
				if(usuario.getTipoUsuario().equals(TipoUsuario.EMPRESA)){
					empresa = empresaDAO.findByIdUsuario( usuario.getIdUsuario() );
				}else if(usuario.getTipoUsuario().equals(TipoUsuario.INSTITUICAO)){
					instituicao = instituicaoDAO.findByIdUsuario( usuario.getIdUsuario() );
				}else if(usuario.getTipoUsuario().equals(TipoUsuario.ALUNO)){
					aluno = alunoDAO.findByIdUsuario( usuario.getIdUsuario() );
				}
				
				FacesContext.getCurrentInstance().getExternalContext().redirect("home.html");
			}else{
				usuario = new Usuario();
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage(FacesMessage.SEVERITY_WARN, "Desculpe!", "Mas o email ou a senha informada não confere. Tente novamente!")));
			}
		} catch (Exception e) {
			e.printStackTrace();
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
	
	public String getNome(){
		if(usuario.getTipoUsuario().equals(TipoUsuario.ALUNO)){
			return aluno.getNome();
		}else{
			return usuario.getNome();
		}
	}
}
