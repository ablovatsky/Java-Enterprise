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
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Repository("workerDao")
public class WorkerDAOImpl extends AbstractDAO<Long, Worker> implements WorkerDAO {

	private static final Logger logger = LoggerFactory.getLogger(WorkerDAOImpl.class);

	@Override
	public Worker findById(Long id) throws DaoException {
		Worker worker = getById(id);
		if(worker!=null){
			Hibernate.initialize(worker.getWorkerProfiles());
			Hibernate.initialize(worker.getSubdivision());
		}
		return worker;
	}

	@Override
	public Worker findBySSOID(String ssoId) throws DaoException {
		logger.info("findBySSOID : {}", ssoId);
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("ssoId", ssoId));
		Worker worker = (Worker)criteria.uniqueResult();
		if(worker!=null){
			Hibernate.initialize(worker.getWorkerProfiles());
			Hibernate.initialize(worker.getSubdivision().getWorkerList());
            logger.info("worker : {}", worker);
		}
		return worker;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Worker> findAll(int count, int setNumber, String sortingColumn, int sortingType) {
		Criteria criteria = createEntityCriteria();
		criteria = new CriteriaBuilder(criteria)
				.addOrder(sortingColumn, sortingType)
				.addCertainNumberRows(count, setNumber)
				.getCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		Set<Worker> workers = new LinkedHashSet<>((List<Worker>) criteria.list());
		for(Worker user : workers){
			Hibernate.initialize(user.getWorkerProfiles());
			Hibernate.initialize(user.getSubdivision());
		}
		return  workers;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Worker> findAllBySubdivisionId(Subdivision subdivision, int count, int setNumber, String sortingColumn, int sortingType) {
		Criteria criteria = createEntityCriteria();
		criteria = new CriteriaBuilder(criteria)
				.addOrder(sortingColumn, sortingType)
				.addCertainNumberRows(count, setNumber)
				.getCriteria();
		criteria.add(Restrictions.eq("subdivision", subdivision));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        Set<Worker> workers = new LinkedHashSet<>((List<Worker>) criteria.list());
		for(Worker user : workers){
			Hibernate.initialize(user.getWorkerProfiles());
			Hibernate.initialize(user.getSubdivision());
		}
		return  workers;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Worker> findAllByLastName(String lastName, int count, int setNumber, String sortingColumn, int sortingType) {
		Criteria criteria = createEntityCriteria();
		criteria = new CriteriaBuilder(criteria)
				.addOrder(sortingColumn, sortingType)
				.addCertainNumberRows(count, setNumber)
				.getCriteria();
		criteria.add(Restrictions.like("lastName", "%" + lastName + "%"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        Set<Worker> workers = new LinkedHashSet<>((List<Worker>) criteria.list());
		for(Worker user : workers){
			Hibernate.initialize(user.getWorkerProfiles());
			Hibernate.initialize(user.getSubdivision());
		}
		return  workers;
	}

	@Override
	public long getCountRows() throws DaoException {
		return UtilDAO.getRowCountInTable(createEntityCriteria());
	}

	@Override
	public long getCountRowsBySubdivisionId(Subdivision subdivision) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("subdivision", subdivision));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return UtilDAO.getRowCountInTable(criteria);
	}

	@Override
	public long getCountRowsByLastName(String lastName) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.like("lastName", "%" + lastName + "%"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return UtilDAO.getRowCountInTable(criteria);
	}

	@Override
	public void add(Worker worker) throws DaoException {
		persistEntity(worker);
	}

    @Override
    public void update(Worker worker) throws DaoException {
        updateEntity(worker);
    }

    @Override
	public void remove(Worker worker) throws DaoException {
		deleteEntity(worker);
	}

}
