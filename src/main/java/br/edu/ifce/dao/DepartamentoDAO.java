package br.edu.ifce.dao;

import java.util.List;

import br.edu.ifce.entity.Departamento;
import br.edu.ifce.util.dao.DAO;
import br.edu.ifce.util.exception.DAOException;

/**
 * 
 * 
 * 
 * @author edivandoalves
 *
 */
public class DepartamentoDAO{

	private DAO<Departamento> dao = new DAO<>(Departamento.class);
	
	public Departamento save(Departamento departamento) throws DAOException {
		if(departamento.getIdDepartamento() == null){
			return dao.add(departamento);
		}else{
			return dao.update(departamento);
		}
	}
	
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
