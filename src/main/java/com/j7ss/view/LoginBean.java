/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;

import com.j7ss.entity.Aluno;
import com.j7ss.entity.Instituicao;
import com.j7ss.entity.TipoUsuario;
import com.j7ss.entity.Usuario;
import com.j7ss.util.DAOException;
import com.j7ss.util.MD5;
import com.j7ss.util.Messages;
import com.j7ss.util.WebContext;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
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
	
	public void login(){	
		try {
			List<Usuario> usuarios = Usuario.findByEmailAndSenha(usuario.getEmail(), MD5.md5(usuario.getSenha()));
			if(usuarios != null && usuarios.size() > 0 ){
				String homePage = "";
				usuario = usuarios.get(0);
				if(usuario.getTipoUsuario().equals(TipoUsuario.ALUNO)){
					aluno = Aluno.findByUsuario( usuario );
					homePage = aluno.isCompleteCadastro() ? "alunoHome.html" : "completeCadastro.html";
				}else if(usuario.getTipoUsuario().equals(TipoUsuario.INSTITUICAO)){
					instituicao = Instituicao.findById(usuario.getIdInstituicao());
					homePage = "instituicaoHome.html";
				}else if(usuario.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR)){
					homePage = "adminHome.html";
				}
				WebContext.redirect(homePage);
			}else{
				usuario = new Usuario();
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage(FacesMessage.SEVERITY_WARN, "Desculpe!", "Mas o email ou a senha informada não confere. Tente novamente!")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void logoff(){
		WebContext.invalidateSession();
		try {
			WebContext.redirect("index.html");
		} catch (IOException e) {
			
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
	
	public void update(){
		try {
			usuario.save();
			if(usuario.getTipoUsuario().equals(TipoUsuario.ALUNO)){
				aluno.usuario(usuario).save();
			}
			aluno.getVagaEstagio().save();
			aluno.getVagaEstagio().getEmpresa().save();
			Messages.showGrowlInfo("Tdsfs", "sadasda");
		} catch (DAOException e) {
			Messages.showGrowlInfo("Tdsfs", "sadasda");
			e.printStackTrace();
		}
	}
	

}
