package com.j7ss.view.professor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.j7ss.core.Messages;
import com.j7ss.core.WebContext;
import com.j7ss.entity.Curso;
import com.j7ss.entity.Professor;
import com.j7ss.entity.constraint.Page;

import lombok.Getter;
import lombok.Setter;


@ManagedBean
@ViewScoped
public class ProfessorCadastroBean implements Serializable {

	//Baseado no AlunoCadastroBean
	private static final long serialVersionUID = 1L;
	
	//Professor que vai ser salvo
	@Setter
	private Professor professor;
	
	//Necessario para o autocomplete
	@Setter @Getter
	private Curso curso = new Curso();

	//Necessario para o autocomplete
	private List<Curso> cursos = Curso.findAll();
	
	
	//Adiciona um curso
	public void addCurso(){
		if(curso != null){

			//Verificar se o professor ja da aula naquele curso
			// se não dar aula adiciona-lo
			if(professor.getCursosDarAula()!=null){
				if(!(professor.getCursosDarAula().contains(curso))){
					professor.addCurso(curso);
				}
			}else{
				//se o array estiver vazio adicionalo
				professor.addCurso(curso);
			}
		}
	}
	
	//Remover um curso
	public void removerCurso(){
		if(curso != null){
			professor.removeCurso(curso);
			curso = null;
		}
	}
	
	public void save(){
		//Eh garantido que o professor tem todos os atributos setados menos os cursos
		if(professor.getCursosDarAula()!=null){
			try {
				professor.save();
				WebContext.redirect(Page.INDEX);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Messages.showGrowlErro("Cadastro de professor", e.getMessage());
			}
		}else{
			FacesContext.getCurrentInstance().addMessage("curso", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Adicione um curso", null));
			
		}
	}
	
	//########
	// Converts autocomplete
	public Curso getCursoByNome(String nome){
		if(cursos != null){
			for (Curso curso : cursos) {
				if(curso.getNome().equals(nome)) return curso;
			}
		}
		return null;
	}
	
	//autocomplete
	//Baseado na mesma função da classe alunoCadastroBean
	public List<Curso> searchCurso(String consulta){
		List<Curso> sugerirCurso = new ArrayList<>();
		
		for (Curso curso : this.cursos) {
			if(curso.getNome().toLowerCase().startsWith(consulta.toLowerCase())){
				sugerirCurso.add(curso);
			}
			
		}
		
		return sugerirCurso;
	}
	
	//## Getters Setters
	public Professor getProfessor() {
		return professor == null ? professor = new Professor() : professor;
	}
	

	public String currentPhase() {
		return "["+FacesContext.getCurrentInstance().getCurrentPhaseId().toString() + "]";
	}
	
	
}
