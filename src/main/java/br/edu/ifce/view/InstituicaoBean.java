package br.edu.ifce.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;
import br.edu.ifce.dao.InstituicaoDAO;
import br.edu.ifce.entity.Instituicao;
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
public class InstituicaoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Setter
	private Instituicao instituicao;
	private List<Instituicao> instituicaos;
	@Getter
	private boolean form = false;
	
	private InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
	
	public void grid(){
		instituicao  = null;
		instituicaos = null;
		form = false;
	}
	
	public void form(){
		form = true;
	}
	
	public List<Instituicao> getInstituicaos() {
		return instituicaos == null ? instituicaos = instituicaoDAO.findAll() : instituicaos;
	}
	
	public void salvar(){
		try {
			instituicaoDAO.save(instituicao);
			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Instituíção", "salva com sucesso!")));
		} catch (DAOException e) {
			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro durante o processo de salvar os dados")));
		}
		grid();
	}
	
	public void excluir(){
		if(instituicao != null){
			try {
				instituicaoDAO.remove(instituicao);
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Instituíção", "Excluído com sucesso!")));
			} catch (DAOException e) {
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro ao excluir")));
			}
		}
		grid();
	}
	
	public Instituicao getInstituicao() {
		return instituicao == null ? instituicao = new Instituicao() : instituicao;
	}
}
