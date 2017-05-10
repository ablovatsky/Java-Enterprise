package by.avectis.contracts.dao.subdivision.impl;

import by.avectis.contracts.dao.AbstractDAO;
import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.dao.subdivision.SubdivisionDAO;
import by.avectis.contracts.model.Subdivision;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Repository("subdivisionDao")
public class SubdivisionDAOImpl extends AbstractDAO<Long, Subdivision> implements SubdivisionDAO {

    private static final Logger logger = LoggerFactory.getLogger(SubdivisionDAOImpl.class);

    @Override
    public void add(Subdivision subdivision) throws DaoException {
        this.persistEntity(subdivision);
    }

    @Override
    public void update(Subdivision subdivision) throws DaoException {
        this.updateEntity(subdivision);
    }

    @Override
    public void remove(Subdivision subdivision) throws DaoException {
        deleteEntity(subdivision);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<Subdivision> findAll() {
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.asc("name"));
        /*Set<Subdivision> subdivisions = (Set<Subdivision>)criteria.list();*/
        /*for (Subdivision subdivision : subdivisions) {
            Hibernate.initialize(subdivision.getWorkerList());
        }*/
        return new LinkedHashSet<>((List<Subdivision>)criteria.list());
    }

    @Override
    public Subdivision findByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        /* if (subdivision != null) {
            Hibernate.initialize(subdivision.getWorkerList());
        }*/
        return (Subdivision) criteria.uniqueResult();
    }

    @Override
    public Subdivision findById(Long id) throws DaoException {
        /*if (subdivision != null) {
            Hibernate.initialize(subdivision.getWorkerList());
        }*/
        return getById(id);
    }
}
