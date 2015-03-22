package br.edu.ifce.dao;

import java.util.List;

import br.edu.ifce.entity.Aluno;
import br.edu.ifce.util.dao.DAO;
import br.edu.ifce.util.exception.DAOException;

/**
 * 
 * 
 * 
 * @author edivandoalves
 *
 */
public class AlunoDAO{

	private DAO<Aluno> dao = new DAO<>(Aluno.class);
	
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
	
	public Aluno findByIdUsuario(Integer idUsuario){
		return dao.findOneByQuery("SELECT a FROM Aluno a WHERE a.idUsuario = ?1" , idUsuario);
	}
}
