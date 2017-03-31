package by.pvt.restaurants.dao.security.impl;

import by.pvt.restaurants.dao.AbstractDao;
import by.pvt.restaurants.dao.security.ProfileDao;
import by.pvt.restaurants.model.Profile;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("profileDao")
public class ProfileDaoImpl extends AbstractDao<Integer, Profile> implements ProfileDao {

	public Profile findById(int id) {
		return getByKey(id);
	}

	public Profile findByType(String type) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("type", type));
		return (Profile) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Profile> findAll(){
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.asc("type"));
		return (List<Profile>)criteria.list();
	}
	
}
