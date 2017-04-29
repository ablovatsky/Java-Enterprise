package by.avectis.contracts.dao;

import by.avectis.contracts.dao.exception.DaoException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractDAO<PK extends Serializable, T> {
	
	private final Class<T> persistentClass;

    @Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public AbstractDAO(){
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}

	private Session getSession(){
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			throw new DaoException(e.toString(), e);
		}
	}

	@SuppressWarnings("unchecked")
	protected T getById(PK key){
		try {
			return (T) getSession().get(persistentClass, key);
		} catch(HibernateException | IllegalArgumentException e) {
			throw new DaoException(e.toString(), e);
		}
	}

	protected void persistEntity(T entity){
        try {
            getSession().persist(entity);
        } catch(HibernateException e) {
            throw new DaoException(e.toString(), e);
        }
	}

	protected void updateEntity(T entity){
        try {
            getSession().update(entity);
        } catch(HibernateException e) {
            throw new DaoException(e.toString(), e);
        }
	}

	protected void deleteEntity(T entity){
        try {
            getSession().delete(entity);
        } catch(HibernateException e) {
            throw new DaoException(e.toString(), e);
        }
	}
	
	protected Criteria createEntityCriteria(){
        try {
            return getSession().createCriteria(persistentClass);
        } catch(HibernateException e) {
            throw new DaoException(e.toString(), e);
        }
	}

}
