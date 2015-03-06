package br.edu.ifce.dao;

import java.util.List;

import br.edu.ifce.entity.VagaEstagioAtividadeDiaria;
import br.edu.ifce.util.dao.GenericDAO;
import br.edu.ifce.util.exception.DAOException;

public class VagaEstagioAtividadeDiariaDAO{

	private GenericDAO<VagaEstagioAtividadeDiaria> dao = new GenericDAO<>(VagaEstagioAtividadeDiaria.class);
	
	public VagaEstagioAtividadeDiaria add(VagaEstagioAtividadeDiaria vaga) throws DAOException {
		return dao.add(vaga);
	}
	
	public VagaEstagioAtividadeDiaria update(VagaEstagioAtividadeDiaria vaga) throws DAOException{
		return dao.update(vaga);
	}
	
	public boolean remove(VagaEstagioAtividadeDiaria vaga) throws DAOException{
		return dao.remove(vaga);
	}

	public List<VagaEstagioAtividadeDiaria> findAll(){
		return dao.findAll();
	}
}
