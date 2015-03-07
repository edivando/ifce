package br.edu.ifce.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;
import br.edu.ifce.dao.EmpresaDAO;
import br.edu.ifce.entity.Empresa;
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
public class EmpresaBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Setter
	private Empresa empresa;
	private List<Empresa> empresas;
	@Getter
	private boolean form = false;
	
	private EmpresaDAO empresaDAO = new EmpresaDAO();
	
	public void grid(){
		empresa  = null;
		empresas = null;
		form = false;
	}
	
	public void form(){
		form = true;
	}
	
	public List<Empresa> getEmpresas() {
		return empresas == null ? empresas = empresaDAO.findAll() : empresas;
	}
	
	public void salvar(){
		try {
			empresaDAO.save(empresa);
			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Empresa", "salva com sucesso!")));
		} catch (DAOException e) {
			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro durante o processo de salvar os dados")));
		}
		grid();
	}
	
	public void excluir(){
		if(empresa != null){
			try {
				empresaDAO.remove(empresa);
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Empresa", "Excluído com sucesso!")));
			} catch (DAOException e) {
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro ao excluir")));
			}
		}
		grid();
	}
	
	public Empresa getEmpresa() {
		return empresa == null ? empresa = new Empresa() : empresa;
	}
}
