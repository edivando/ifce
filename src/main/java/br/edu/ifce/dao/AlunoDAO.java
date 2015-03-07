package br.edu.ifce.dao;

import java.util.List;

import br.edu.ifce.entity.Aluno;
import br.edu.ifce.util.dao.GenericDAO;
import br.edu.ifce.util.exception.DAOException;

/**
 * 
 * 
 * 
 * @author edivandoalves
 *
 */
public class AlunoDAO{

	private GenericDAO<Aluno> dao = new GenericDAO<>(Aluno.class);
	
	public Aluno save(Aluno aluno) throws DAOException {
		if(aluno.getIdAluno() == null){
			return dao.add(aluno);
		}else{
			return dao.update(aluno);
		}
	}
	
	public Aluno add(Aluno aluno) throws DAOException {
		return dao.add(aluno);
	}
	
	public Aluno update(Aluno aluno) throws DAOException{
		return dao.update(aluno);
	}
	
	public boolean remove(Aluno aluno) throws DAOException{
		return dao.remove(aluno);
	}

	public List<Aluno> findAll(){
		return dao.findAll();
	}
	
}
