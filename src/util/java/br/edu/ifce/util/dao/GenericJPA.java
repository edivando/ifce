package br.edu.ifce.util.dao;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe de utilidades para a integração e persistencia com o banco de dados.
 * 
 * @author Edivando Alves
 * 
 */
public class GenericJPA implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8732971614557917884L;

	private static GenericJPA jpa;

	private EntityManagerFactory entityManagerFactory;

	private EntityManager entityManager;

	public static Logger log = Logger.getLogger(GenericJPA.class.getSimpleName());

	/**
	 * Construtor privado implementando singleton
	 * 
	 */
	private GenericJPA() {
		init();
	}
	
	private void init(){
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("findme");
		}
	}

	/**
	 * Obtem uma instancia única de JPAUtis
	 * 
	 * @return {@link JPAUtis}
	 */
	public static GenericJPA getInstance() {
		if (jpa == null) {
			jpa = new GenericJPA();
		}
		return jpa;
	}

	/**
	 * Obtem um entityManagerFactory
	 * 
	 * @return {@link EntityManagerFactory}
	 */
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
	
	/**
	 * Reinicia a conexão com o banco de dados, para o caso onde o servidor finalizou a conexão.
	 * 
	 */
	public void newEntityManagerFactory(){
		entityManagerFactory = null;
		init();
	}
	
	/**
	 * Ontem um {@link EntityManager} e inicia uma transaçao.
	 * 
	 * @return {@link EntityManager}
	 */
	public EntityManager beginManager() {
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		return entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void rollBackManager() {
		entityManager.getTransaction().rollback();
		entityManager.close();
	}

	/**
	 * Comita e fecha o entityManager
	 */
	public void closeManager() {
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
