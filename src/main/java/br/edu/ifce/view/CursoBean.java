package br.edu.ifce.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;
import br.edu.ifce.dao.CursoDAO;
import br.edu.ifce.entity.Curso;
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
public class CursoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Setter
	private Curso curso;
	private List<Curso> cursos;
	@Getter
	private boolean form = false;
	
	private CursoDAO cursoDAO = new CursoDAO();
	
	public void grid(){
		curso  = null;
		cursos = null;
		form = false;
	}
	
	public void form(){
		form = true;
	}
	
	public List<Curso> getCursos() {
		return cursos == null ? cursos = cursoDAO.findAll() : cursos ;
	}
	
	public void salvar(){
		try {
			cursoDAO.save(curso);
			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Curso", "salvo com sucesso!")));
		} catch (DAOException e) {
			FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro durante o processo de salvar os dados")));
		}
		grid();
	}
	
	public void excluir(){
		if(curso != null){
			try {
				cursoDAO.remove(curso);
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Curso", "Excluído com sucesso!")));
			} catch (DAOException e) {
				FacesContext.getCurrentInstance().addMessage(null, (new FacesMessage("Erro ao excluir")));
			}
		}
		grid();
	}

	public Curso getCurso() {
		return curso == null ? curso = new Curso() : curso;
	}
	
}
