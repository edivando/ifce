package com.j7ss.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Fetch;

import com.j7ss.core.DAO;
import com.j7ss.core.DAOException;
import com.j7ss.core.IGenericEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "professor")
@ToString @EqualsAndHashCode(of="id")
public class Professor implements IGenericEntity<Professor>{
	//Usando como base a classe Aluno
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer id;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String qualificacao;
	
	@Getter @Setter
	private Integer idade;
	
	//Um professor pode dar aula em varios cursos. Um curso tem varios professores.
	//Tambem foi adicionado na classe curso pois usando o mappedBy quando removia um curso
	//removia todos os outros cursos que o professor estava relacionado
	@Getter @Setter
	@ManyToMany 
	@Fetch(FetchMode.JOIN)
	@JoinTable(name="professor_curso", joinColumns={@JoinColumn(name="professor_id")}, inverseJoinColumns={@JoinColumn(name="curso_id")})
	List<Curso> cursosDarAula;
		
	@Override
	public boolean isNew() {
		//Como foi baseado na classe aluno fiz a mesma regra 
		//se id == null salva um novo
		// caso contrario atualiza pelo id
		return id == null;
	}
	
	//Adicionar um curso no professor
	public Professor addCurso(Curso curso){
		//Caso a lista estiver vazia, instancia-la
		if(cursosDarAula == null){
			cursosDarAula = new ArrayList<Curso>();
		}
		this.cursosDarAula.add(curso);
		return this;
	}
	
	//Remover um curso no professor
	public Professor removeCurso(Curso curso){
		if(cursosDarAula != null){
			this.cursosDarAula.remove(curso);
		}
		return this;
	}

	//## DAO
	private static DAO<Professor> dao = new DAO<Professor>(Professor.class);

	//Salvar professor
	@Override
	public Professor save() throws DAOException {
		// TODO Auto-generated method stub
		return isNew() ? dao.add(this) : dao.update(this);
	}
	
	//Remover professor
	@Override
	public boolean remove() throws DAOException {
		// TODO Auto-generated method stub
		return dao.remove(this);
	}

	//Lista todos
	public static List<Professor> findAll(){
		return dao.findByQuery("SELECT a FROM Professor a"); 
	}
	
	//Procura um pelo id
	public static Professor findById(Integer idProfessor){
		return dao.findOne(idProfessor);
	}
	

}
