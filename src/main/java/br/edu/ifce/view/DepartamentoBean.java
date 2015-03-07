package br.edu.ifce.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;
import br.edu.ifce.dao.DepartamentoDAO;
import br.edu.ifce.entity.Departamento;
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
public class DepartamentoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Setter
	private Departamento departamento;
	private List<Departamento> departamentos;
	@Getter
	private boolean form = false;
	
	private DepartamentoDAO departamentoDAO = new DepartamentoDAO();
	
	public void grid(){
		departamento  = null;
		departamentos = null;
		form = false;
	}
	
	public void form(){
		form = true;
	}
	
	public List<Departamento> getDepartamentos() {
		return departamentos == null ? departamentos = departamentoDAO.findAll() : departamentos;
	}
	
	public void salvar(){
		try {
			departamentoDAO.save(departamento);
			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Departamento", "salvo com sucesso!")));
		} catch (DAOException e) {
			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro durante o processo de salvar os dados")));
		}
		grid();
	}
	
	public void excluir(){
		if(departamento != null){
			try {
				departamentoDAO.remove(departamento);
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Departamento", "Excluído com sucesso!")));
			} catch (DAOException e) {
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro ao excluir")));
			}
		}
		grid();
	}

	public Departamento getDepartamento() {
		return departamento == null ? departamento = new Departamento() : departamento;
	}	
}
