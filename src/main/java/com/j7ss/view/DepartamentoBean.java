package com.j7ss.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.j7ss.entity.Departamento;
import com.j7ss.util.BasicView;
import com.j7ss.util.Messages;

/**
 * 
 * 
 * 
 * @author edivandoalves
 *
 */
@ManagedBean
@ViewScoped
public class DepartamentoBean extends BasicView<Departamento>{
	private static final long serialVersionUID = 1L;
	
	@Override
	public Departamento getEntity() {
		return entity == null ? entity = new Departamento() : entity;
	}
	
	@Override
	public List<Departamento> getEntitys() {
		return entitys == null ? entitys = Departamento.findAll() : entitys;
	}
	
	@Override
	public void onSave() {
		Messages.showGrowlInfo("Departamento", "Departamento <strong>{0}</strong> salvo com sucesso!", entity.getNome());
	}
	
	@Override
	public void onRemove(Departamento departamento) {
		Messages.showGrowlInfo("Departamento", "Departamento <strong>{0}</strong> removido com sucesso!", departamento.getNome());
	}
	
	
	
//	@Setter
//	private Departamento departamento;
//	private List<Departamento> departamentos;
//	@Getter
//	private boolean form = false;
//	
////	private DepartamentoDAO departamentoDAO = new DepartamentoDAO();
//	
//	public void grid(){
//		departamento  = null;
//		departamentos = null;
//		form = false;
//	}
//	
//	public void form(){
//		form = true;
//	}
//	
//	public List<Departamento> getDepartamentos() {
//		return departamentos == null ? departamentos = Departamento.findAll() : departamentos;
//	}
//	
//	public void salvar(){
//		try {
//			departamento.save();
//			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Departamento", "salvo com sucesso!")));
//		} catch (DAOException e) {
//			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro durante o processo de salvar os dados")));
//		}
//		grid();
//	}
//	
//	public void excluir(){
//		if(departamento != null){
//			try {
//				departamento.remove();
//				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Departamento", "Excluído com sucesso!")));
//			} catch (DAOException e) {
//				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro ao excluir")));
//			}
//		}
//		grid();
//	}
//
//	public Departamento getDepartamento() {
//		return departamento == null ? departamento = new Departamento() : departamento;
//	}	
}
