package by.avectis.contracts.dao.worker.impl;

import by.avectis.contracts.dao.AbstractDAO;
import by.avectis.contracts.model.Profile;
import by.avectis.contracts.dao.worker.ProfileDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("profileDao")
public class ProfileDAOImpl extends AbstractDAO<Long, Profile> implements ProfileDAO {

	@Override
	public Profile findById(Long id) {
		return getById(id);
	}

	@Override
	public Profile findByType(String type) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("type", type));
		return (Profile) criteria.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Profile> findAll(){
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.asc("type"));
		return (List<Profile>)criteria.list();
	}
	
}
