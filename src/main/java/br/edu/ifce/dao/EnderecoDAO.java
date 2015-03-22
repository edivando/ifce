package br.edu.ifce.dao;

import java.util.List;

import br.edu.ifce.entity.Endereco;
import br.edu.ifce.util.dao.DAO;
import br.edu.ifce.util.exception.DAOException;

/**
 * 
 * 
 * 
 * @author edivandoalves
 *
 */
public class EnderecoDAO{

	private DAO<Endereco> dao = new DAO<>(Endereco.class);
	
	public Endereco save(Endereco endereco) throws DAOException {
		if(endereco.getIdEndereco() == null){
			return dao.add(endereco);
		}else{
			return dao.update(endereco);
		}
	}
	
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
