package by.avectis.contracts.dao.timeSheet.timeSheetImpl;

import by.avectis.contracts.dao.AbstractDAO;
import by.avectis.contracts.dao.Util.CriteriaBuilder;
import by.avectis.contracts.dao.Util.UtilDAO;
import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.dao.timeSheet.TimeSheetDAO;
import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.model.TimeSheet;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Repository("TimeSheetDAO")
public class TimeSheetDAOImpl extends AbstractDAO<Long, TimeSheet> implements TimeSheetDAO {

    @Override
    public void add(TimeSheet timeSheet) throws DaoException {
        persistEntity(timeSheet);
    }

    @Override
    public void update(TimeSheet timeSheet) throws DaoException {
        updateEntity(timeSheet);
    }

    @Override
    public void remove(TimeSheet timeSheet) throws DaoException {
        deleteEntity(timeSheet);
    }

    @Override
    public TimeSheet findById(long id) throws DaoException {
        return getById(id);
    }

    @Override
    public TimeSheet findByDate(LocalDate date) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("date", date));
        return (TimeSheet) criteria.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<TimeSheet> findAllBySubdivision(Subdivision subdivision, int count, int setNumber, String sortingColumn, int sortingType) {
        Criteria criteria = createEntityCriteria();
        criteria = new CriteriaBuilder(criteria)
                .addOrder(sortingColumn, sortingType)
                .addCertainNumberRows(count, setNumber)
                .getCriteria();
        criteria.add(Restrictions.eq("subdivision", subdivision));
        //timeSheetSet.forEach(timeSheet -> Hibernate.initialize(timeSheet.getEmploymentSet()));
        return new LinkedHashSet<>((List<TimeSheet>) criteria.list());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<TimeSheet> findAll(int count, int setNumber, String sortingColumn, int sortingType) {
        Criteria criteria = createEntityCriteria();
        criteria = new CriteriaBuilder(criteria)
                .addOrder(sortingColumn, sortingType)
                .addCertainNumberRows(count, setNumber)
                .getCriteria();
        return new HashSet<>( (List<TimeSheet>) criteria.list());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<TimeSheet> findAllByState(Boolean state, int count, int setNumber, String sortingColumn, int sortingType) {
        Criteria criteria = createEntityCriteria();
        criteria = new CriteriaBuilder(criteria)
                .addOrder(sortingColumn, sortingType)
                .addCertainNumberRows(count, setNumber)
                .getCriteria();
        criteria.add(Restrictions.eq("state", state));
        Set<TimeSheet> timeSheetSet = new LinkedHashSet<>((List<TimeSheet>) criteria.list());
        timeSheetSet.forEach(timeSheet -> Hibernate.initialize(timeSheet.getEmploymentSet()));
        return timeSheetSet;
    }

    @Override
    public long getCountRows() {
        return UtilDAO.getRowCountInTable(createEntityCriteria());
    }

    @Override
    public long getCountRowsBySubdivision(Subdivision subdivision) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("subdivision", subdivision));
        return  UtilDAO.getRowCountInTable(criteria);
    }

    @Override
    public long getCountRowsByState(Boolean state) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("state", state));
        return UtilDAO.getRowCountInTable(criteria);
    }


}
