package br.edu.ifce.dao;

import java.util.List;

import br.edu.ifce.entity.Usuario;
import br.edu.ifce.util.dao.DAO;
import br.edu.ifce.util.exception.DAOException;

/**
 * 
 * 
 * 
 * @author edivandoalves
 *
 */
public class UsuarioDAO{

	private DAO<Usuario> dao = new DAO<>(Usuario.class);
	
	public Usuario save(Usuario usuario) throws DAOException {
		if(usuario.getIdUsuario() == null){
			return dao.add(usuario);
		}else{
			return dao.update(usuario);
		}
	}
	
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
	
	public List<Usuario> findByEmailAndSenha(String email, String senha){
		return dao.findByQuery("SELECT u FROM Usuario u WHERE u.email = ?1 and u.senha = ?2", email, senha);
	}
}
