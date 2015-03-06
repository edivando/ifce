package br.edu.ifce.dao;

import java.util.List;

import br.edu.ifce.entity.Empresa;
import br.edu.ifce.util.dao.GenericDAO;
import br.edu.ifce.util.exception.DAOException;

public class EmpresaDAO{

	private GenericDAO<Empresa> dao = new GenericDAO<>(Empresa.class);
	
	public Empresa add(Empresa empresa) throws DAOException {
		return dao.add(empresa);
	}
	
	public Empresa update(Empresa empresa) throws DAOException{
		return dao.update(empresa);
	}
	
	public boolean remove(Empresa empresa) throws DAOException{
		return dao.remove(empresa);
	}

	public List<Empresa> findAll(){
		return dao.findAll();
	}
}
