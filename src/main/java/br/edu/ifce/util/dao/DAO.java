package br.edu.ifce.util.dao;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;

import br.edu.ifce.util.entity.IGenericEntity;
import br.edu.ifce.util.exception.DAOException;

/**
 * 
 * @author edivandoalves
 *
 * @param <T>
 */
public class DAO<T extends IGenericEntity<T>>{

	private final Class<T> clazz;
	
	private GenericJPA jpaUtil;
	
	public DAO(Class<T> clazz) {
		this.jpaUtil = GenericJPA.getInstance();
		this.clazz = clazz;   //(Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public T add(T entity) throws DAOException {
		EntityManager entityManager = getEMFactory().createEntityManager();
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			return entity;
		}catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	}
	
	 public List<T> add(List<T> list) throws DAOException{
		EntityManager entityManager = getEMFactory().createEntityManager();
		entityManager.getTransaction().begin();
		try {
			for (T t : list) {
				entityManager.persist(t);
			}
			entityManager.getTransaction().commit();
			return list;
		}catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	 }

	 
	@SuppressWarnings("unchecked")
	public void add(T ...entitys) throws DAOException{
		EntityManager entityManager = getEMFactory().createEntityManager();
		entityManager.getTransaction().begin();
		try {
			for (T iGenericEntity : entitys) {
				entityManager.persist(iGenericEntity);
			}
			entityManager.getTransaction().commit();
		}catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	 }
	 
	public boolean remove(Integer id) throws DAOException {
		EntityManager entityManager = getEMFactory().createEntityManager();
		entityManager.getTransaction().begin();
		try {
			entityManager.remove(findOne(id));
			entityManager.getTransaction().commit();
			return true;
		}catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	}

	public boolean remove(T entity) throws DAOException {
		EntityManager entityManager = getEMFactory().createEntityManager();
		entityManager.getTransaction().begin();
		try {
			T entityToBeRemoved = entityManager.merge(entity);
			entityManager.remove(entityToBeRemoved);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	}

	public T update(T entity) throws DAOException {
		EntityManager entityManager = getEMFactory().createEntityManager();
		entityManager.getTransaction().begin();
		try {
			entityManager.merge(entity);
			entityManager.getTransaction().commit();
			return entity;
		}catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> List<T> findByNamedQuery(int maxResult, String namedQuery, Object... parametros) {
		EntityManager entityManager = getEMFactory().createEntityManager();
		try {
			Query query = entityManager.createNamedQuery(namedQuery, clazz);
			for (int i = 0; i < parametros.length; i++) {
				query.setParameter(i + 1, parametros[i]);
			}
			if(maxResult != 0){
				query.setMaxResults(maxResult);
			}
			return query.getResultList();
		}finally{
			entityManager.close();
		}
	}
	
	@SuppressWarnings("hiding")
	public <T> List<T> findByNamedQuery(String namedQuery, Object... parametros) {
		return findByNamedQuery(0, namedQuery, parametros);
	}

	@SuppressWarnings("hiding")
	public <T> List<T> findByQuery(String select, Object... parametros){
		return findByQuery(0, select, parametros);
	}
	
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> List<T> findByQuery(int maxResult, String select, Object... parametros) {
		EntityManager entityManager = getEMFactory().createEntityManager();
		try {
			Query query = entityManager.createQuery(select, clazz);
			for (int i = 0; i < parametros.length; i++) {
				query.setParameter(i + 1, parametros[i]);
			}
			if(maxResult != 0){
				query.setMaxResults(maxResult);
			}
			return query.getResultList();
			
		}catch (NoResultException e){
			return null;
		}finally{
			entityManager.close();
		}
	}
	
	@SuppressWarnings("hiding")
	public <T> List<T> findByNativeQuery(String select, Object... parametros) {
		return findByNativeQuery(0, select, parametros);
	}
	
	
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> List<T> findByNativeQuery(int maxResult, String select, Object... parametros) {
		EntityManager entityManager = getEMFactory().createEntityManager();
		try {
			Query query = entityManager.createNativeQuery(select, clazz);
			for (int i = 0; i < parametros.length; i++) {
				query.setParameter(i + 1, parametros[i]);
			}
			if(maxResult != 0){
				query.setMaxResults(maxResult);
			}
			return query.getResultList();
		}catch (NoResultException e){
			return null;
		}finally{
			entityManager.close();
		}
	}

	@SuppressWarnings("unchecked")
	public T findOneByNamedQuery(String namedQuery, Object... parametros) {
		EntityManager entityManager = getEMFactory().createEntityManager();
		try {
			Query query = entityManager.createNamedQuery(namedQuery, clazz);
			for (int i = 0; i < parametros.length; i++) {
				query.setParameter(i + 1, parametros[i]);
			}
			return (T) query.getSingleResult();
		}catch (NoResultException e){
			return null;
		}finally{
			entityManager.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public T findOneByQuery(String select, Object... parametros) {
		EntityManager entityManager = getEMFactory().createEntityManager();
		try {
			Query query = entityManager.createQuery(select, clazz);
			for (int i = 0; i < parametros.length; i++) {
				query.setParameter(i + 1, parametros[i]);
			}
			return (T) query.getSingleResult();
		}catch (NoResultException e){
			return null;
		}finally{
			entityManager.close();
		}
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> T findOne(Serializable pk) {
		EntityManager entityManager = getEMFactory().createEntityManager();
		try {
			return (T) entityManager.find(clazz, pk);
		}finally{
			entityManager.close();
		}
	}

	public T findOne(Integer id){
  		return (T) findOne(id);
	}
	
	public List<T> findAll() {
		return findByQuery("SELECT x FROM "+clazz.getSimpleName()+" x");
	}

	protected EntityManager beginManager() {
		return jpaUtil.beginManager();
	}

	protected EntityManagerFactory getEMFactory() {
		return jpaUtil.getEntityManagerFactory();
	}
	
	public void renewEMFactory() throws DAOException{
		jpaUtil.newEntityManagerFactory();
	}

	public EntityManager getManager() {
		return jpaUtil.getEntityManager();
	}
	
	public Session getSession(){
		return (Session) getManager().getDelegate();
	}
	
	protected void rollBackManager() {
		jpaUtil.rollBackManager();
	}

	protected void closeManager() {
		jpaUtil.closeManager();
	}
}

class GenericJPA implements Serializable{

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
			entityManagerFactory = Persistence.createEntityManagerFactory("ifce_estagio_postgree");
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
