package by.avectis.contracts.dao.worker.impl;

import by.avectis.contracts.dao.AbstractDAO;
import by.avectis.contracts.dao.Util.CriteriaBuilder;
import by.avectis.contracts.dao.Util.UtilDAO;
import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.model.Worker;
import by.avectis.contracts.dao.worker.WorkerDAO;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("workerDao")
public class WorkerDAOImpl extends AbstractDAO<Long, Worker> implements WorkerDAO {

	private static final Logger logger = LoggerFactory.getLogger(WorkerDAOImpl.class);

	@Override
	public Worker findWorkerById(Long id) throws DaoException {
		Worker user = getById(id);
		if(user!=null){
			Hibernate.initialize(user.getWorkerProfiles());
			Hibernate.initialize(user.getSubdivision());
		}
		return user;
	}

	@Override
	public Worker findWorkerBySSO(String sso) throws DaoException {
		logger.info("SSO : {}", sso);
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("ssoId", sso));
		Worker user = (Worker)criteria.uniqueResult();
		if(user!=null){
			Hibernate.initialize(user.getWorkerProfiles());
			Hibernate.initialize(user.getSubdivision().getWorkerList());
		}
		return user;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Worker> findAllWorkers(int count, int setNumber, String sortingColumn, int sortingType) throws DaoException {
		Criteria criteria = createEntityCriteria();
		criteria = new CriteriaBuilder(criteria)
				.addOrder(sortingColumn, sortingType)
				.addCertainNumberRows(count, setNumber)
				.getCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Worker> users = (List<Worker>) criteria.list();
		for(Worker user : users){
			Hibernate.initialize(user.getWorkerProfiles());
			Hibernate.initialize(user.getSubdivision());
		}
		return  users;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Worker> findAllWorkersBySubdivisionId(Subdivision subdivision, int count, int setNumber, String sortingColumn, int sortingType) throws DaoException {
		Criteria criteria = createEntityCriteria();
		criteria = new CriteriaBuilder(criteria)
				.addOrder(sortingColumn, sortingType)
				.addCertainNumberRows(count, setNumber)
				.getCriteria();
		criteria.add(Restrictions.eq("subdivision", subdivision));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Worker> users = (List<Worker>) criteria.list();
		for(Worker user : users){
			Hibernate.initialize(user.getWorkerProfiles());
			Hibernate.initialize(user.getSubdivision());
		}
		return  users;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Worker> findAllWorkersByLastName(String lastName, int count, int setNumber, String sortingColumn, int sortingType) throws DaoException {
		Criteria criteria = createEntityCriteria();
		criteria = new CriteriaBuilder(criteria)
				.addOrder(sortingColumn, sortingType)
				.addCertainNumberRows(count, setNumber)
				.getCriteria();
		criteria.add(Restrictions.like("lastName", "%" + lastName + "%"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Worker> users = (List<Worker>) criteria.list();
		for(Worker user : users){
			Hibernate.initialize(user.getWorkerProfiles());
			Hibernate.initialize(user.getSubdivision());
		}
		return  users;
	}

	@Override
	public long getCountWorkers() throws DaoException {
		return UtilDAO.getRowCountInTable(createEntityCriteria());
	}

	@Override
	public long getCountWorkers(Subdivision subdivision) throws DaoException {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("subdivision", subdivision));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return UtilDAO.getRowCountInTable(criteria);
	}

	@Override
	public long getCountWorkers(String lastName) throws DaoException {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.like("lastName", "%" + lastName + "%"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return UtilDAO.getRowCountInTable(criteria);
	}

	@Override
	public void addWorker(Worker worker) throws DaoException {
		this.persist(worker);
	}

    @Override
    public void updateWorker(Worker worker) throws DaoException {
        update(worker);
    }

    @Override
	public void deleteWorker(Worker worker) throws DaoException {
		delete(worker);
	}

}
