package com.j7ss.entity;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.j7ss.core.DAOException;

public class ProfessorTest {
	
	@Test
	public void testSaveProfessor() {
		//Testando o metodo save
		System.out.println("Teste save professor");
		//Instanciando um professor
		Professor professor = new Professor();
		professor.setIdade(24);
		professor.setNome("Nome");
		professor.setQualificacao("Qualificação");
		try {
			//Salvando professor
			professor.save();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Verifico se o professor foi salvo buscando ele no banco
		Professor professorResult = Professor.findById(professor.getId());
		//Confiro se o resultado é igual ao professor salvo
		assertTrue( professorResult.equals(professor));
		removeAllProfessor();
	}
	
	@Test
	public void testSaveProfessorCurso(){
		System.out.println("Teste save professor curso");
		List<Curso> cursos = new ArrayList<Curso>();
		//Instanciando 5 cursos
		for(int i=0; i<5;i++){
			Curso curso = new Curso();
			curso.setNome("Nome_curso_"+i);
			cursos.add(curso);
		}
		try {
			//Salvando
			for (Curso curso : cursos) {
				curso.save();
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Instanciando professor
		Professor professor = new Professor();
		professor.setIdade(24);
		professor.setNome("Nome_professor");
		professor.setQualificacao("Qualificação");
		//Setando os cursos
		professor.setCursosDarAula(cursos);
		
		try {
			//Salvando professor
			professor.save();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Verifico se o professor foi salvo buscando ele no banco
		Professor professorResul = Professor.findById(professor.getId());
		//Confiro se o resultado é igual ao professor salvo
		assertTrue(professorResul.equals(professor));
		
		removeAllCursos();
		removeAllProfessor();
		
	}
	
	@Test
	public void testRemoveCursoProfessor(){
		
		System.out.println("Teste remover curso");
		List<Curso> cursos = new ArrayList<Curso>();
		//Instanciando 5 cursos
		for(int i=0; i<5;i++){
			Curso curso = new Curso();
			curso.setNome("Nome_curso_"+i);
			cursos.add(curso);
		}
		try {
			//Salvando
			for (Curso curso : cursos) {
				curso.save();
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Instanciando professor
		Professor professor = new Professor();
		professor.setIdade(24);
		professor.setNome("Nome_professor");
		professor.setQualificacao("Qualificação");
		//Setando os cursos
		professor.setCursosDarAula(cursos);
		
		try {
			//Salvando professor
			professor.save();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			professor.getCursosDarAula().get(0).remove();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Verifico se o professor foi salvo buscando ele no banco
		Professor professorResul = Professor.findById(professor.getId());
		
		//Confiro se o numero de cursos são diferentes
		assertTrue(professorResul.cursosDarAula.size()!=professor.cursosDarAula.size());
		
		removeAllCursos();
		removeAllProfessor();
	}
	
	@Test
	public void testUpdateProfessor(){
		System.out.println("Teste Update professor");
		
		//Instanciando professor
		Professor professor = new Professor();
		professor.setIdade(24);
		professor.setNome("Nome_professor");
		professor.setQualificacao("Qualificação");
		
		try {
			//Salvando professor
			professor.save();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//salvo a idade antiga
		int idade = professor.getIdade();
		//Aumento a idade em uma unidade
		professor.setIdade(idade+1);
		
		try {
			//Salvo as alterações
			professor.save();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//Procuro o com as mudanças no banco
		professor = Professor.findById(professor.getId());
		//Verifico se a idade esta diferente da antiga
		assertTrue(idade != professor.getIdade());
		
		removeAllProfessor();
	}

	public void removeAllProfessor(){
		System.out.println("Teste remove todos os professores");
		//Removendo todos os professores criados nos testes
		List<Professor> professores = Professor.findAll();
		try {
			for (Professor professor : professores) {
				assertTrue(professor.remove());
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeAllCursos(){
		//Removendo todos os cursos criados nos testes
		List<Curso> cursos = Curso.findAll();
		for (Curso curso : cursos) {
			try {
				assertTrue(curso.remove());
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
