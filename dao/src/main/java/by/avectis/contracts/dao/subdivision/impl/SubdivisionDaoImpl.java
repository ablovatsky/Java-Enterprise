package by.avectis.contracts.dao.subdivision.impl;

import by.avectis.contracts.dao.AbstractDAO;
import by.avectis.contracts.dao.subdivision.SubdivisionDao;
import by.avectis.contracts.model.Subdivision;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("subdivisionDao")
public class SubdivisionDaoImpl extends AbstractDAO<Long, Subdivision> implements SubdivisionDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<Subdivision> findAll() {
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.asc("name"));
        List<Subdivision> subdivisions = (List<Subdivision>)criteria.list();
       /* for (Subdivision subdivision : subdivisions) {
            Hibernate.initialize(subdivision.getWorkerList());
        }*/
        return subdivisions;
    }

    @Override
    public Subdivision findByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        Subdivision subdivision = (Subdivision) criteria.uniqueResult();
        if (subdivision != null) {
            Hibernate.initialize(subdivision.getWorkerList());
        }
        return subdivision;
    }

    @Override
    public Subdivision findById(Long id) {
        Subdivision subdivision = getById(id);
        if (subdivision != null) {
            Hibernate.initialize(subdivision.getWorkerList());
        }
        return subdivision;
    }
}
