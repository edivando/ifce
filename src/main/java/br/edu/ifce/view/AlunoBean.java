package br.edu.ifce.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;
import br.edu.ifce.dao.AlunoDAO;
import br.edu.ifce.entity.Aluno;
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
public class AlunoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Setter
	private Aluno aluno;
	private List<Aluno> alunos;
	@Getter
	private boolean form = false;
	
	private AlunoDAO alunoDAO = new AlunoDAO();
	
	public void grid(){
		aluno  = null;
		alunos = null;
		form = false;
	}
	
	public void form(){
		form = true;
	}
	
	public List<Aluno> getAlunos() {
		return alunos == null ? alunos = alunoDAO.findAll() : alunos ;
	}
	
	public void salvar(){
		try {
			alunoDAO.save(aluno);
			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Aluno", "salvo com sucesso!")));
		} catch (DAOException e) {
			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro durante o processo de salvar os dados")));
		}
		grid();
	}
	
	public void excluir(){
		if(aluno != null){
			try {
				alunoDAO.remove(aluno);
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Aluno", "Excluído com sucesso!")));
			} catch (DAOException e) {
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro ao excluir")));
			}
		}
		grid();
	}

	public Aluno getAluno() {
		return aluno == null ? aluno = new Aluno() : aluno;
	}
}
