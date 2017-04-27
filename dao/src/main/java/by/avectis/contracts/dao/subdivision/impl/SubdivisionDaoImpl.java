package by.avectis.contracts.dao.subdivision.impl;

import by.avectis.contracts.dao.AbstractDAO;
import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.dao.subdivision.SubdivisionDao;
import by.avectis.contracts.model.Subdivision;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("subdivisionDao")
public class SubdivisionDaoImpl extends AbstractDAO<Long, Subdivision> implements SubdivisionDao {

    private static final Logger logger = LoggerFactory.getLogger(SubdivisionDaoImpl.class);

    @Override
    public void addSubdivision(Subdivision subdivision) throws DaoException {
        this.persist(subdivision);
    }

    @Override
    public void updateSubdivision(Subdivision subdivision) throws DaoException {
        this.update(subdivision);
    }

    @Override
    public void deleteSubdivision(Subdivision subdivision) throws DaoException {
        this.delete(subdivision);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Subdivision> findAllSubdivisions() throws DaoException {
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.asc("name"));
        List<Subdivision> subdivisions = (List<Subdivision>)criteria.list();
        /*for (Subdivision subdivision : subdivisions) {
            Hibernate.initialize(subdivision.getWorkerList());
        }*/
        return subdivisions;
    }

    @Override
    public Subdivision findSubdivisionByName(String name) throws DaoException {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        Subdivision subdivision = (Subdivision) criteria.uniqueResult();
        if (subdivision != null) {
            Hibernate.initialize(subdivision.getWorkerList());
        }
        return subdivision;
    }

    @Override
    public Subdivision findSubdivisionById(Long id) throws DaoException {
        Subdivision subdivision = getById(id);
        if (subdivision != null) {
            Hibernate.initialize(subdivision.getWorkerList());
        }
        return subdivision;
    }
}
