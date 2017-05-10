package by.avectis.contracts.dao.subdivision.impl;

import by.avectis.contracts.dao.AbstractDAO;
import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.dao.subdivision.SubdivisionCostDAO;
import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.model.SubdivisionCost;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Repository("SubdivisionCostDAO")
public class SubdivisionCostDAOImpl extends AbstractDAO<Long, SubdivisionCost> implements SubdivisionCostDAO{
    @Override
    public void add(SubdivisionCost subdivisionCost) throws DaoException {
        persistEntity(subdivisionCost);
    }

    @Override
    public void update(SubdivisionCost subdivisionCost) throws DaoException {
        updateEntity(subdivisionCost);
    }

    @Override
    public void remove(SubdivisionCost subdivisionCost) throws DaoException {
        deleteEntity(subdivisionCost);
    }

    @Override
    public SubdivisionCost findById(long id) throws DaoException {
        return getById(id);
    }

    @Override
    public SubdivisionCost findBySubdivisionAndDate(Subdivision subdivision, LocalDate date) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("subdivision", subdivision));
        criteria.add(Restrictions.eq("date", date));
        return (SubdivisionCost) criteria.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<SubdivisionCost> findAllByDate(LocalDate date) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("date", date));
        /*subdivisionCostSet.forEach(subdivisionCost -> Hibernate.initialize(subdivisionCost.getSubdivision()));*/
        return new LinkedHashSet<>((List<SubdivisionCost>) criteria.list());
    }
}
