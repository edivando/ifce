package br.edu.ifce.dao;

import java.util.List;

import br.edu.ifce.entity.Instituicao;
import br.edu.ifce.util.dao.GenericDAO;
import br.edu.ifce.util.exception.DAOException;

public class InstituicaoDAO{

	private GenericDAO<Instituicao> dao = new GenericDAO<>(Instituicao.class);
	
	public Instituicao add(Instituicao instituicao) throws DAOException {
		return dao.add(instituicao);
	}
	
	public Instituicao update(Instituicao instituicao) throws DAOException{
		return dao.update(instituicao);
	}
	
	public boolean remove(Instituicao instituicao) throws DAOException{
		return dao.remove(instituicao);
	}

	public List<Instituicao> findAll(){
		return dao.findAll();
	}
}
