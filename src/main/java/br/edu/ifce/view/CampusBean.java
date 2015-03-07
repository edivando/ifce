package br.edu.ifce.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;
import br.edu.ifce.dao.CampusDAO;
import br.edu.ifce.entity.Campus;
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
public class CampusBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Setter
	private Campus campu;
	private List<Campus> campus;
	@Getter
	private boolean form = false;
	
	private CampusDAO campusDAO = new CampusDAO();
	
	public void grid(){
		campu  = null;
		campus = null;
		form = false;
	}
	
	public void form(){
		form = true;
	}
	
	public List<Campus> getCampus() {
		return campus == null ? campus = campusDAO.findAll() : campus;
	}
	
	public void salvar(){
		try {
			campusDAO.save(campu);
			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Campus", "salvo com sucesso!")));
		} catch (DAOException e) {
			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro durante o processo de salvar os dados")));
		}
		grid();
	}
	
	public void excluir(){
		if(campu != null){
			try {
				campusDAO.remove(campu);
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Vaga de Estágio", "Excluído com sucesso!")));
			} catch (DAOException e) {
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro ao excluir")));
			}
		}
		grid();
	}

	public Campus getCampu() {
		return campu == null ? campu = new Campus() : campu;
	}
}
