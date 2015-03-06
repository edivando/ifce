package br.edu.ifce.dao;

import java.util.List;

import br.edu.ifce.entity.Campus;
import br.edu.ifce.util.dao.GenericDAO;
import br.edu.ifce.util.exception.DAOException;

public class CampusDAO{

	private GenericDAO<Campus> dao = new GenericDAO<>(Campus.class);
	
	public Campus add(Campus campus) throws DAOException {
		return dao.add(campus);
	}
	
	public Campus update(Campus campus) throws DAOException{
		return dao.update(campus);
	}
	
	public boolean remove(Campus campus) throws DAOException{
		return dao.remove(campus);
	}

	public List<Campus> findAll(){
		return dao.findAll();
	}
}
