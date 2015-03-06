package br.edu.ifce.dao;

import java.util.List;

import br.edu.ifce.entity.Endereco;
import br.edu.ifce.util.dao.GenericDAO;
import br.edu.ifce.util.exception.DAOException;

public class EnderecoDAO{

	private GenericDAO<Endereco> dao = new GenericDAO<>(Endereco.class);
	
	public Endereco add(Endereco endereco) throws DAOException {
		return dao.add(endereco);
	}
	
	public Endereco update(Endereco endereco) throws DAOException{
		return dao.update(endereco);
	}
	
	public boolean remove(Endereco endereco) throws DAOException{
		return dao.remove(endereco);
	}

	public List<Endereco> findAll(){
		return dao.findAll();
	}
}
