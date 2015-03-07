package br.edu.ifce.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;
import br.edu.ifce.dao.VagaEstagioAtividadeDiariaDAO;
import br.edu.ifce.entity.VagaEstagioAtividadeDiaria;
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
public class VagaEstagioAtividadeDiariaBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Setter
	private VagaEstagioAtividadeDiaria atividade;
	private List<VagaEstagioAtividadeDiaria> atividades;
	@Getter
	private boolean form = false;
	
	private VagaEstagioAtividadeDiariaDAO atividadeDAO = new VagaEstagioAtividadeDiariaDAO();
	
	public void grid(){
		atividade  = null;
		atividades = null;
		form = false;
	}
	
	public void form(){
		form = true;
	}
	
	public List<VagaEstagioAtividadeDiaria> getAtividades() {
		return atividades == null ? atividades = atividadeDAO.findAll() : atividades;
	}
	
	public void salvar(){
		try {
			atividadeDAO.save(atividade);
			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Atividade Diária", "salva com sucesso!")));
		} catch (DAOException e) {
			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro durante o processo de salvar os dados")));
		}
		grid();
	}
	
	public void excluir(){
		if(atividade != null){
			try {
				atividadeDAO.remove(atividade);
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Atividade", "Excluído com sucesso!")));
			} catch (DAOException e) {
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro ao excluir")));
			}
		}
		grid();
	}

	public VagaEstagioAtividadeDiaria getAtividade() {
		return atividade == null ? atividade = new VagaEstagioAtividadeDiaria() : atividade;
	}
}
