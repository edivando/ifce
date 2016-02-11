/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.j7ss.entity.Empresa;
import com.j7ss.util.BasicView;
import com.j7ss.util.Messages;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class EmpresaBean extends BasicView<Empresa>{
	private static final long serialVersionUID = 1L;
	
	@Override
	public Empresa getEntity() {
		return entity == null ? entity = new Empresa() : entity;
	}
	
	@Override
	public List<Empresa> getEntitys() {
		return entitys == null ? entitys = Empresa.findAll() : entitys;
	}
	
	@Override
	public void onSave() {
		Messages.showGrowlInfo("Empresa", "Empresa <strong>{0}</strong> salvo com sucesso!", entity.getNome());
	}
	
	@Override
	public void onRemove(Empresa empresa) {
		Messages.showGrowlInfo("Empresa", "Empresa <strong>{0}</strong> removido com sucesso!", empresa.getNome());
	}
	
//	@Setter
//	private Empresa empresa;
//	private List<Empresa> empresas;
//	@Getter
//	private boolean form = false;
//	
////	private EmpresaDAO empresaDAO = new EmpresaDAO();
//	
//	public void grid(){
//		empresa  = null;
//		empresas = null;
//		form = false;
//	}
//	
//	public void form(){
//		form = true;
//	}
//	
//	public List<Empresa> getEmpresas() {
//		return empresas == null ? empresas = Empresa.findAll() : empresas;
//	}
//	
//	public void salvar(){
//		try {
//			empresa.save();
//			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Empresa", "salva com sucesso!")));
//		} catch (DAOException e) {
//			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro durante o processo de salvar os dados")));
//		}
//		grid();
//	}
//	
//	public void excluir(){
//		if(empresa != null){
//			try {
//				empresa.remove();
//				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Empresa", "Excluído com sucesso!")));
//			} catch (DAOException e) {
//				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro ao excluir")));
//			}
//		}
//		grid();
//	}
//	
//	public Empresa getEmpresa() {
//		return empresa == null ? empresa = new Empresa() : empresa;
//	}
}
