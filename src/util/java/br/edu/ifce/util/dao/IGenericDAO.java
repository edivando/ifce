package br.edu.ifce.util.dao;

import java.io.Serializable;
import java.util.List;

import br.edu.ifce.util.exception.DAOException;

/**
 * 
 * 
 * @author edivandoalves
 *
 * @param <T>
 */
public interface IGenericDAO<T> extends Serializable{
        
    public T add(T entity) throws DAOException;
    
    public List<T> add(List<T> list) throws DAOException;
    
    public boolean remove(Integer id) throws DAOException;
    
    public boolean remove(T entity) throws DAOException;
        
    public T update(T entity) throws DAOException;
    
    public T find(Integer id) throws DAOException;

    public List<T> list() throws DAOException;

}