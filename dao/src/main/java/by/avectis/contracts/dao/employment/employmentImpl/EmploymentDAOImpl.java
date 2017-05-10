package by.avectis.contracts.dao.employment.employmentImpl;

import by.avectis.contracts.dao.AbstractDAO;
import by.avectis.contracts.dao.employment.EmploymentDAO;
import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.model.Contract;
import by.avectis.contracts.model.Employment;
import by.avectis.contracts.model.TimeSheet;
import by.avectis.contracts.model.Worker;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("EmploymentDAO")
public class EmploymentDAOImpl extends AbstractDAO<Long, Employment> implements EmploymentDAO {

    @Override
    public void add(Employment employment) throws DaoException {
        persistEntity(employment);
    }

    @Override
    public void update(Employment employment) throws DaoException {
        updateEntity(employment);
    }

    @Override
    public void remove(Employment employment) throws DaoException {
        deleteEntity(employment);
    }

    @Override
    public Employment findById(long id) throws DaoException {
        return getById(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<Employment> findByWorker(Worker worker) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("worker", worker));
        return new HashSet<>( (List<Employment>) criteria.list());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<Employment> findByContract(Contract contract) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("contract", contract));
        return new HashSet<>( (List<Employment>) criteria.list());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<Employment> findByTimeSheet(TimeSheet timeSheet) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("timeSheet", timeSheet));
        return new HashSet<>( (List<Employment>) criteria.list());
    }
}
