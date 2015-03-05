package br.edu.ifce.util.exception;

/**
 * 
 * 
 * 
 * @author Edivando Alves
 *
 */
public class DAOException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DAOException() {
		super();
	}
	
	public DAOException(String message){
		super(message);
	}
	
	public DAOException(Throwable e){
		super(e);
	}
	
	public DAOException(String message, Throwable e){
		super(message, e);
	}
}
