package by.avectis.contracts.dao.security.impl;

import by.avectis.contracts.dao.AbstractDao;
import by.avectis.contracts.model.Worker;
import by.avectis.contracts.dao.security.WorkerDao;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("workerDao")
public class WorkerDaoImpl extends AbstractDao<Long, Worker> implements WorkerDao {

	private static final Logger logger = LoggerFactory.getLogger(WorkerDaoImpl.class);

	@Override
	public Worker findById(Long id) {
		Worker user = getById(id);
		if(user!=null){
			Hibernate.initialize(user.getWorkerProfiles());
		}
		return user;
	}

	@Override
	public Worker findBySSO(String sso) {
		logger.info("SSO : {}", sso);
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("ssoId", sso));
		Worker user = (Worker)criteria.uniqueResult();
		if(user!=null){
			Hibernate.initialize(user.getWorkerProfiles());
		}
		return user;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Worker> findAllWorkers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Worker> users = (List<Worker>) criteria.list();
		for(Worker user : users){
			Hibernate.initialize(user.getWorkerProfiles());
		}
		return (List<Worker>) criteria.list();
	}

	@Override
	public void addWorker(Worker worker) {
		this.persist(worker);
	}

    @Override
    public void updateWorker(Worker worker) {
        update(worker);
    }

    @Override
	public void deleteBySSO(String sso) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("ssoId", sso));
		Worker user = (Worker)criteria.uniqueResult();
		delete(user);
	}

}
