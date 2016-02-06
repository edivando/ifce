package com.j7ss.view;



import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.j7ss.entity.VagaEstagio;
import com.j7ss.util.DAOException;

import lombok.Getter;
import lombok.Setter;

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
	
//	private VagaEstagioDAO vagaEstagioDAO = new VagaEstagioDAO();
	
	public void grid(){
		vagaEstagio  = null;
		vagaEstagios = null;
		form = false;
	}
	
	public void form(){
		form = true;
	}
	
	public List<VagaEstagio> getVagaEstagios() {
		return vagaEstagios == null ? vagaEstagios = VagaEstagio.findAll() : vagaEstagios ;
	}
	
	public void salvar(){
		try {
			vagaEstagio.save();
			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Vaga de Estágio", "salvo com sucesso!")));
		} catch (DAOException e) {
			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro durante o processo de salvar os dados")));
		}
		grid();
	}
	
	public void excluir(){
		if(vagaEstagio != null){
			try {
				vagaEstagio.remove();
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Vaga de Estágio", "Excluído com sucesso!")));
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
