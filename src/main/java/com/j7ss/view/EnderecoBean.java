package com.j7ss.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.j7ss.entity.Endereco;
import com.j7ss.util.BasicView;

/**
 * 
 * 
 * 
 * @author edivandoalves
 *
 */
@ManagedBean
@ViewScoped
public class EnderecoBean extends BasicView<Endereco>{
	private static final long serialVersionUID = 1L;
	
	@Override
	public Endereco getEntity() {
		return entity == null ? entity = new Endereco() : entity;
	}
	
	@Override
	public List<Endereco> getEntitys() {
		return entitys == null ? entitys = Endereco.findAll() : entitys;
	}
	
//	@Setter
//	private Endereco endereco;
//	private List<Endereco> enderecos;
//	@Getter
//	private boolean form = false;
//	
////	private EnderecoDAO enderecoDAO = new EnderecoDAO();
//	
//	public void grid(){
//		endereco  = null;
//		enderecos = null;
//		form = false;
//	}
//	
//	public void form(){
//		form = true;
//	}
//	
//	public List<Endereco> getEnderecos() {
//		return enderecos == null ? enderecos = Endereco.findAll() : enderecos ;
//	}
//	
//	public void salvar(){
//		try {
//			endereco.save();
//			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Endereço", "salvo com sucesso!")));
//		} catch (DAOException e) {
//			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro durante o processo de salvar os dados")));
//		}
//		grid();
//	}
//	
//	public void excluir(){
//		if(endereco != null){
//			try {
//				endereco.remove();
//				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Endereco", "Excluído com sucesso!")));
//			} catch (DAOException e) {
//				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro ao excluir")));
//			}
//		}
//		grid();
//	}
//
//	public Endereco getEndereco() {
//		return endereco == null ? endereco = new Endereco() : endereco;
//	}	
}
