package by.avectis.contracts.dao.contracts.impl;

import by.avectis.contracts.dao.AbstractDAO;
import by.avectis.contracts.dao.Util.CriteriaBuilder;
import by.avectis.contracts.dao.Util.UtilDAO;
import by.avectis.contracts.dao.contracts.ContractDAO;
import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.model.Contract;
import by.avectis.contracts.model.ContractState;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Repository("ContractDAO")
public class ContractDAOImpl extends AbstractDAO<Long, Contract> implements ContractDAO {

    private static final Logger logger = LoggerFactory.getLogger(ContractDAOImpl.class);

    @Override
    public void add(Contract contract) throws DaoException {
        persistEntity(contract);
    }

    @Override
    public void update(Contract contract) throws DaoException {
        updateEntity(contract);
    }

    @Override
    public void remove(Contract contract) throws DaoException {
        deleteEntity(contract);
    }

    @Override
    public Contract findById(long id) throws DaoException {
        return getById(id);
    }

    @Override
    public Contract findByName(String name) {
        logger.info("findByName : {}", name);
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        return (Contract)criteria.uniqueResult();

    }

    @Override
    public Contract findByAxCode(String axCode) {
        logger.info("findByAxCode : {}", axCode);
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("axCode", axCode));
        return (Contract)criteria.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<Contract> findAll(int count, int setNumber, String sortingColumn, int sortingType) {
        logger.info("findAll : {}");
        Criteria criteria = createEntityCriteria();
        criteria = new CriteriaBuilder(criteria)
                .addOrder(sortingColumn, sortingType)
                .addCertainNumberRows(count, setNumber)
                .getCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return new LinkedHashSet<>((List<Contract>) criteria.list());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<Contract> findAllByName(String name, int count, int setNumber, String sortingColumn, int sortingType) {
        logger.info("findAll : {}");
        Criteria criteria = createEntityCriteria();
        criteria = new CriteriaBuilder(criteria)
                .addOrder(sortingColumn, sortingType)
                .addCertainNumberRows(count, setNumber)
                .getCriteria();
        criteria.add(Restrictions.like("name", name));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return new LinkedHashSet<>((List<Contract>) criteria.list());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<Contract> findAllByAxCode(String axCode, int count, int setNumber, String sortingColumn, int sortingType) {
        logger.info("findAllByAxCode : {}");
        Criteria criteria = createEntityCriteria();
        criteria = new CriteriaBuilder(criteria)
                .addOrder(sortingColumn, sortingType)
                .addCertainNumberRows(count, setNumber)
                .getCriteria();
        criteria.add(Restrictions.like("axCode", axCode));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return new LinkedHashSet<>((List<Contract>) criteria.list());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<Contract> findAllByState(ContractState contractState, int count, int setNumber, String sortingColumn, int sortingType) {
        logger.info("findAllByState : {}");
        Criteria criteria = createEntityCriteria();
        criteria = new CriteriaBuilder(criteria)
                .addOrder(sortingColumn, sortingType)
                .addCertainNumberRows(count, setNumber)
                .getCriteria();
        criteria.add(Restrictions.eq("state", contractState));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return new LinkedHashSet<>((List<Contract>) criteria.list());
    }

    @Override
    public long getCountRows() throws DaoException {
        return UtilDAO.getRowCountInTable(createEntityCriteria());
    }

    @Override
    public long getCountRowsByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.like("name", name));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return UtilDAO.getRowCountInTable(criteria);
    }

    @Override
    public long getCountRowsByAxCode(String axCode) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.like("axCode", axCode));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return UtilDAO.getRowCountInTable(criteria);
    }

    @Override
    public long getCountRowsByState(ContractState contractState) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("state", contractState));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return UtilDAO.getRowCountInTable(criteria);
    }
}
