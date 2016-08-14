package com.j7ss.view.admin;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.j7ss.core.BasicView;
import com.j7ss.core.Messages;
import com.j7ss.entity.Curso;
import com.j7ss.entity.Professor;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
public class AdminProfessorBean extends BasicView<Professor>{
	private static final long serialVersionUID = 1L;
	
	//******************************************************************************************************************************
	//Modo "administrador" controle total de todos os professores adicionados
	//Baseado na classe AdminAlunoBean
	
	
	//Necessario para fazer alterações nos cursos que o professor dar aula
	@Getter @Setter
	private Curso curso = new Curso();
	
	//Necesario para que não seja possivel adicionar cursos inexistentes
	private List<Curso> cursos = Curso.findAll();
	
	//## Growl Messages
	//Remover curso de um professor
	public String removerCurso(Curso curso){
		entity.getCursosDarAula().remove(curso);
		return "";
	}

	//Adicionar um curso
	public void addCurso(){
		for(Curso curso : cursos){
			//Busca o curso pelo nome. Podia ter feito uma query no hibernate mais fiquei sem tempo
			if(curso.getNome().equals(curso.getNome())){

				//Verificar se o professor ja da aula naquele curso
				// se não dar aula adiciona-lo
				if(entity.getCursosDarAula()!=null){
					if(!(entity.getCursosDarAula().contains(curso))){
						entity.addCurso(curso);
						break;
					}
				}
				//se o array estiver vazio adicionalo
				entity.addCurso(curso);
				break;
			}
		}
	}
	
	
	@Override
	public void onRemove(Professor entity) {
		Messages.showGrowlInfo("Professor", "Professor <strong>{0}</strong> removido com sucesso!", entity.getNome());
	}

	//******************************************************************************************************************************
	//## Getters Setters
	@Override
	public Professor getEntity() {
		return entity == null ? entity = new Professor() : entity;
	}

	@Override
	public List<Professor> getEntitys() {
		return entitys == null ? entitys = Professor.findAll() : entitys;
		
	}
	
	
}
