package br.edu.ifce.dao;

import java.util.List;

import br.edu.ifce.entity.VagaEstagio;
import br.edu.ifce.util.dao.DAO;
import br.edu.ifce.util.exception.DAOException;

/**
 * 
 * 
 * 
 * @author edivandoalves
 *
 */
public class VagaEstagioDAO{

	private DAO<VagaEstagio> dao = new DAO<VagaEstagio>(VagaEstagio.class);
	
	public VagaEstagio save(VagaEstagio vagaEstagio) throws DAOException {
		if(vagaEstagio.getIdVaga() == null){
			return dao.add(vagaEstagio);
		}else{
			return dao.update(vagaEstagio);
		}
	}
	
	public VagaEstagio add(VagaEstagio vaga) throws DAOException {
		return dao.add(vaga);
	}
	
	public VagaEstagio update(VagaEstagio vaga) throws DAOException{
		return dao.update(vaga);
	}
	
	public boolean remove(VagaEstagio vaga) throws DAOException{
		return dao.remove(vaga);
	}

	public List<VagaEstagio> findAll(){
		return dao.findAll();
	}
}
