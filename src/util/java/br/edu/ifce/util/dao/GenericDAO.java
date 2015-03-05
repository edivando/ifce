package br.edu.ifce.util.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
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
public abstract class GenericDAO<T extends IGenericEntity<T>> implements IGenericDAO<T>{

	private static final long serialVersionUID = 1L;

	private final Class<T> clazz;
	
	private GenericJPA jpaUtil;

	/**
	 * Contrutor recebendo obrigatoriamente um EntityManager
	 * 
	 * @param manager EntityManager
	 */
	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.jpaUtil = GenericJPA.getInstance();
		this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * Adiciona uma entidade qualqer no banco de dados
	 * 
	 * @param entity T
	 * @return T
	 * @throws JPAException
	 */
	public T add(T entity) throws DAOException {
		EntityManager entityManager = getEMFactory().createEntityManager();
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			return entity;
		}catch(PersistenceException e){
			renewEMFactory();
			throw new DAOException("Aguarde um momento, estamos reconectando com o servidor!", e); 
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	}
	
	/**
	 * Adiciona uma lista de <T> no banco de dados
	 * 
	 * @param list {@link List} of <T>
	 * @return list {@link List} of <T>
	 * @throws DAOException
	 */
	 public List<T> add(List<T> list) throws DAOException{
		EntityManager entityManager = getEMFactory().createEntityManager();
		entityManager.getTransaction().begin();
		try {
			for (T t : list) {
				entityManager.persist(t);
			}
			entityManager.getTransaction().commit();
			return list;
		}catch(PersistenceException e){
			renewEMFactory();
			throw new DAOException("Aguarde um momento, estamos reconectando com o servidor!", e); 
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	 }

	 
	 public void add(IGenericEntity...entitys) throws DAOException{
		EntityManager entityManager = getEMFactory().createEntityManager();
		entityManager.getTransaction().begin();
		try {
			for (IGenericEntity iGenericEntity : entitys) {
				entityManager.persist(iGenericEntity);
			}
			entityManager.getTransaction().commit();
		}catch(PersistenceException e){
			renewEMFactory();
			throw new DAOException("Aguarde um momento, estamos reconectando com o servidor!", e); 
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	 }
	 
	/**
	 * Remove qualquer entidade do banco de dados a partir do id
	 * 
	 * @param Long id
	 * @throws JPAException
	 */
	public boolean remove(Integer id) throws DAOException {
		EntityManager entityManager = getEMFactory().createEntityManager();
		entityManager.getTransaction().begin();
		try {
			entityManager.remove(find(id));
			entityManager.getTransaction().commit();
			return true;
		}catch(PersistenceException e){
			renewEMFactory();
			throw new DAOException("Aguarde um momento, estamos reconectando com o servidor!",e); 
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	}

	/**
	 * Remove qualquer entidade do banco de dados a apartir da propria entidade.
	 * 
	 * @param entity T
	 * @throws JPAException
	 */
	public boolean remove(T entity) throws DAOException {
		EntityManager entityManager = getEMFactory().createEntityManager();
		entityManager.getTransaction().begin();
		try {
			T entityToBeRemoved = entityManager.merge(entity);
			entityManager.remove(entityToBeRemoved);
			entityManager.getTransaction().commit();
			return true;
		}catch(PersistenceException e){
			renewEMFactory();
			throw new DAOException("Aguarde um momento, estamos reconectando com o servidor!", e); 
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	}

	/**
	 * Atualiza qualquer entidade no banco de dados
	 * 
	 * @param entity T
	 * @return T
	 * @throws JPAException
	 */
	public T update(T entity) throws DAOException {
		EntityManager entityManager = getEMFactory().createEntityManager();
		entityManager.getTransaction().begin();
		try {
			entityManager.merge(entity);
			entityManager.getTransaction().commit();
			return entity;
		}catch(PersistenceException e){
			renewEMFactory();
			throw new DAOException("A conexão com o banco de dados será reiniciada!", e); 
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	}

	/**
	 * Obtem uma lista de qualquer entidade da aplica��o.
	 * 
	 * @param clazz
	 *            O Class da entidade que quero listar
	 * @param jpql
	 *            A consulta jpql
	 * @param parametros
	 *            Os parametros dessa consulta
	 * @return Uma lista de entidades.
	 * @throws JPAException
	 */
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> List<T> listByNamedQuery(int maxResult, String namedQuery, Object... parametros) throws DAOException {
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
		}catch(PersistenceException e){
			renewEMFactory();
			throw new DAOException("Aguarde um momento, estamos reconectando com o servidor!", e); 
		} catch (Exception e) {
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	}
	
	@SuppressWarnings("hiding")
	public <T> List<T> listByNamedQuery(String namedQuery, Object... parametros) throws DAOException {
		return listByNamedQuery(0, namedQuery, parametros);
	}

	@SuppressWarnings("hiding")
	public <T> List<T> listByQuery(String select, Object... parametros) throws DAOException {
		return listByQuery(0, select, parametros);
	}
	
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> List<T> listByQuery(int maxResult, String select, Object... parametros) throws DAOException {
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
		}catch(PersistenceException e){
			renewEMFactory();
			throw new DAOException("Aguarde um momento, estamos reconectando com o servidor!", e); 
		} catch (Exception e) {
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	}
	
	@SuppressWarnings("hiding")
	public <T> List<T> listByNativeQuery(String select, Object... parametros) throws DAOException {
		return listByNativeQuery(0, select, parametros);
	}
	
	
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> List<T> listByNativeQuery(int maxResult, String select, Object... parametros) throws DAOException {
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
		}catch(PersistenceException e){
			renewEMFactory();
			throw new DAOException("Aguarde um momento, estamos reconectando com o servidor!", e); 
		} catch (Exception e) {
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	}

	@SuppressWarnings("unchecked")
	public T getEntity(String namedQuery, Object... parametros) throws DAOException {
		EntityManager entityManager = getEMFactory().createEntityManager();
		try {
			Query query = entityManager.createNamedQuery(namedQuery, clazz);
			for (int i = 0; i < parametros.length; i++) {
				query.setParameter(i + 1, parametros[i]);
			}
			return (T) query.getSingleResult();
		}catch (NoResultException e){
			return null;
		}catch(PersistenceException e){
			renewEMFactory();
			throw new DAOException("Aguarde um momento, estamos reconectando com o servidor!", e); 
		} catch (Exception e) {
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public T getEntityQuery(String select, Object... parametros) throws DAOException {
		EntityManager entityManager = getEMFactory().createEntityManager();
		try {
			Query query = entityManager.createQuery(select, clazz);
			for (int i = 0; i < parametros.length; i++) {
				query.setParameter(i + 1, parametros[i]);
			}
			return (T) query.getSingleResult();
		}catch (NoResultException e){
			return null;
		}catch(PersistenceException e){
			renewEMFactory();
			throw new DAOException("Aguarde um momento, estamos reconectando com o servidor!", e); 
		} catch (Exception e) {
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	}

	/**
	 * Obtem uma �nica entidade, passando como parametro o Class e a primary key
	 * <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/youbode_music7" />
			<property name="javax.persistence.jdbc.user" value="root" />
			<property name="javax.persistence.jdbc.password" value="root" />
	 * @param clazz
	 *            Class da entidade
	 * @param pk
	 *            Atributo identificador
	 * @return T
	 * @throws JPAException
	 */
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> T getEntity(Serializable pk) throws DAOException {
		EntityManager entityManager = getEMFactory().createEntityManager();
		try {
			return (T) entityManager.find(clazz, pk);
		}catch(PersistenceException e){
			renewEMFactory();
			throw new DAOException("Aguarde um momento, estamos reconectando com o servidor!", e); 
		} catch (Exception e) {
			throw new DAOException(e);
		}finally{
			entityManager.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public T find(Integer id) throws DAOException {
		try {
  			return (T) getEntity(id);
		}catch(PersistenceException e){
			renewEMFactory();
			throw new DAOException("Aguarde um momento, estamos reconectando com o servidor!", e); 
		} catch (DAOException e) {
			throw new DAOException(e);
		}
	}
	
	public List<T> list() throws DAOException {
		try {
			return listByNamedQuery(clazz.getSimpleName());
		}catch(PersistenceException e){
			renewEMFactory();
			throw new DAOException("Aguarde um momento, estamos reconectando com o servidor!", e); 
		} catch (DAOException e) {
			throw new DAOException(e);
		}
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
