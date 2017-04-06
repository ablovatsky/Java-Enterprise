package by.avectis.contracts.dao.security.impl;

import by.avectis.contracts.dao.AbstractDao;
import by.avectis.contracts.dao.security.SubdivisionDao;
import by.avectis.contracts.model.Subdivision;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("subdivisionDao")
public class SubdivisionDaoImpl extends AbstractDao<Long, Subdivision> implements SubdivisionDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<Subdivision> findAll() {
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.asc("name"));
        return (List<Subdivision>)criteria.list();
    }

    @Override
    public Subdivision findByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        return (Subdivision) criteria.uniqueResult();
    }

    @Override
    public Subdivision findById(Long id) {
        return getById(id);
    }
}
