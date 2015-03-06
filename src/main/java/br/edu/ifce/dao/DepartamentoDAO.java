package br.edu.ifce.dao;

import java.util.List;

import br.edu.ifce.entity.Departamento;
import br.edu.ifce.util.dao.GenericDAO;
import br.edu.ifce.util.exception.DAOException;

public class DepartamentoDAO{

	private GenericDAO<Departamento> dao = new GenericDAO<>(Departamento.class);
	
	public Departamento add(Departamento departamento) throws DAOException {
		return dao.add(departamento);
	}
	
	public Departamento update(Departamento departamento) throws DAOException{
		return dao.update(departamento);
	}
	
	public boolean remove(Departamento departamento) throws DAOException{
		return dao.remove(departamento);
	}

	public List<Departamento> findAll(){
		return dao.findAll();
	}
}
