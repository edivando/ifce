package br.edu.ifce.dao;

import java.util.List;

import br.edu.ifce.entity.Curso;
import br.edu.ifce.util.dao.GenericDAO;
import br.edu.ifce.util.exception.DAOException;

public class CursoDAO{

	private GenericDAO<Curso> dao = new GenericDAO<>(Curso.class);
	
	public Curso add(Curso curso) throws DAOException {
		return dao.add(curso);
	}
	
	public Curso update(Curso curso) throws DAOException{
		return dao.update(curso);
	}
	
	public boolean remove(Curso curso) throws DAOException{
		return dao.remove(curso);
	}

	public List<Curso> findAll(){
		return dao.findAll();
	}
}
