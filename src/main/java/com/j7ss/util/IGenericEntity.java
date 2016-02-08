package com.j7ss.util;

import java.io.Serializable;

/**
 * 
 * 
 * @author Edivando
 *
 * @param <T>
 */
public interface IGenericEntity<T> extends Serializable{

	boolean isNew();
	
	
	T save() throws DAOException;
	
	boolean remove() throws DAOException;
}
