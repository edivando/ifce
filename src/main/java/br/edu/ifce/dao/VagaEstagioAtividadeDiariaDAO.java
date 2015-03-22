package br.edu.ifce.dao;

import java.util.List;

import br.edu.ifce.entity.VagaEstagioAtividadeDiaria;
import br.edu.ifce.util.dao.DAO;
import br.edu.ifce.util.exception.DAOException;

/**
 * 
 * 
 * 
 * @author edivandoalves
 *
 */
public class VagaEstagioAtividadeDiariaDAO{

	private DAO<VagaEstagioAtividadeDiaria> dao = new DAO<>(VagaEstagioAtividadeDiaria.class);
	
	public VagaEstagioAtividadeDiaria save(VagaEstagioAtividadeDiaria atividade) throws DAOException {
		if(atividade.getIdAtividade() == null){
			return dao.add(atividade);
		}else{
			return dao.update(atividade);
		}
	}
	
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
