package br.edu.ifce.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;
import br.edu.ifce.dao.VagaEstagioDAO;
import br.edu.ifce.entity.VagaEstagio;
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
public class VagaEstagioBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Setter
	private VagaEstagio vagaEstagio;
	private List<VagaEstagio> vagaEstagios;
	@Getter
	private boolean form = false;
	
	private VagaEstagioDAO vagaEstagioDAO = new VagaEstagioDAO();
	
	public void grid(){
		vagaEstagio  = null;
		vagaEstagios = null;
		form = false;
	}
	
	public void form(){
		form = true;
	}
	
	public List<VagaEstagio> getVagaEstagios() {
		return vagaEstagios == null ? vagaEstagios = vagaEstagioDAO.findAll() : vagaEstagios ;
	}
	
	public void salvar(){
		if(vagaEstagio.getIdVaga() == null){
			try {
				vagaEstagioDAO.add(vagaEstagio);
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Tipo de Usu�rio", "Adcicionado com sucesso!")));
			} catch (DAOException e) {
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro ao atualizar")));
			}
		}else{
			try {
				vagaEstagioDAO.update(vagaEstagio);
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Tipo de Usu�rio", "Atualizado com sucesso!")));
			} catch (DAOException e) {
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro ao atualizar")));
			}
		}
		grid();
	}
	
	public void excluir(){
		if(vagaEstagio != null){
			try {
				vagaEstagioDAO.remove(vagaEstagio);
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Tipo de Usu�rio", "Exclu�do com sucesso!")));
			} catch (DAOException e) {
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro ao excluir")));
			}
		}
		grid();
	}

	public VagaEstagio getVagaEstagio() {
		return (vagaEstagio == null) ? vagaEstagio = new VagaEstagio() : vagaEstagio;
	}
}
