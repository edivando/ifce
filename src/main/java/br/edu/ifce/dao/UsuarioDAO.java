package br.edu.ifce.dao;

import java.util.List;

import br.edu.ifce.entity.Usuario;
import br.edu.ifce.util.dao.GenericDAO;
import br.edu.ifce.util.exception.DAOException;

public class UsuarioDAO{

	private GenericDAO<Usuario> dao = new GenericDAO<>(Usuario.class);
	
	public Usuario add(Usuario usuario) throws DAOException {
		return dao.add(usuario);
	}
	
	public Usuario update(Usuario usuario) throws DAOException{
		return dao.update(usuario);
	}
	
	public boolean remove(Usuario usuario) throws DAOException{
		return dao.remove(usuario);
	}

	public List<Usuario> findAll(){
		return dao.findAll();
	}
}
