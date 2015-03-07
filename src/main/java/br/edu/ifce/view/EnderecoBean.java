package br.edu.ifce.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;
import br.edu.ifce.dao.EnderecoDAO;
import br.edu.ifce.entity.Endereco;
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
public class EnderecoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Setter
	private Endereco endereco;
	private List<Endereco> enderecos;
	@Getter
	private boolean form = false;
	
	private EnderecoDAO enderecoDAO = new EnderecoDAO();
	
	public void grid(){
		endereco  = null;
		enderecos = null;
		form = false;
	}
	
	public void form(){
		form = true;
	}
	
	public List<Endereco> getEnderecos() {
		return enderecos == null ? enderecos = enderecoDAO.findAll() : enderecos ;
	}
	
	public void salvar(){
		try {
			enderecoDAO.save(endereco);
			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Endereço", "salvo com sucesso!")));
		} catch (DAOException e) {
			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro durante o processo de salvar os dados")));
		}
		grid();
	}
	
	public void excluir(){
		if(endereco != null){
			try {
				enderecoDAO.remove(endereco);
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Endereco", "Excluído com sucesso!")));
			} catch (DAOException e) {
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro ao excluir")));
			}
		}
		grid();
	}

	public Endereco getEndereco() {
		return endereco == null ? endereco = new Endereco() : endereco;
	}	
}
