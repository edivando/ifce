package br.edu.ifce.dao;

import java.util.List;

import br.edu.ifce.entity.Curso;
import br.edu.ifce.util.dao.DAO;
import br.edu.ifce.util.exception.DAOException;

/**
 * 
 * 
 * 
 * @author edivandoalves
 *
 */
public class CursoDAO{

	private DAO<Curso> dao = new DAO<>(Curso.class);
	
	public Curso save(Curso curso) throws DAOException {
		if(curso.getIdCurso() == null){
			return dao.add(curso);
		}else{
			return dao.update(curso);
		}
	}
	
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
