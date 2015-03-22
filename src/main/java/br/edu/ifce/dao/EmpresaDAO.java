package br.edu.ifce.dao;

import java.util.List;

import br.edu.ifce.entity.Empresa;
import br.edu.ifce.util.dao.DAO;
import br.edu.ifce.util.exception.DAOException;

/**
 * 
 * 
 * 
 * @author edivandoalves
 *
 */
public class EmpresaDAO{

	private DAO<Empresa> dao = new DAO<>(Empresa.class);
	
	public Empresa save(Empresa empresa) throws DAOException {
		if(empresa.getIdEmpresa() == null){
			return dao.add(empresa);
		}else{
			return dao.update(empresa);
		}
	}
	
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
	
	public Empresa findByIdUsuario(Integer idUsuario){
		return dao.findOneByQuery("SELECT e FROM Empresa e WHERE e.idUsuario = ?1" , idUsuario);
	}
	
	
	
}
