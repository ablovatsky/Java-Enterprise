package by.avectis.contracts.dao.contracts.impl;


import by.avectis.contracts.dao.AbstractDAO;
import by.avectis.contracts.dao.Util.CriteriaBuilder;
import by.avectis.contracts.dao.contracts.ContractStateDAO;
import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.model.ContractState;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.List;

@Repository("ContractsStateDAO")
public class ContractStateDAOImpl extends AbstractDAO<Long, ContractState> implements ContractStateDAO {

    private static final Logger logger = LoggerFactory.getLogger(ContractDAOImpl.class);

    @Override
    @SuppressWarnings("unchecked")
    public List<ContractState> findAll() {
        logger.info("findAll : {}");
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<ContractState> contractStates = (List<ContractState>) criteria.list();
        //contractStates.forEach(contractState -> Hibernate.initialize(contractState.getContractsList()));
        return contractStates;
    }

    @Override
    public ContractState findById(long id) throws DaoException{
        ContractState contractState = getById(id);
        if (contractState != null) {
            Hibernate.initialize(contractState.getContractsList());
        }
        return contractState;
    }

    @Override
    public ContractState findByName(String nameType) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.like("type", nameType));
        return (ContractState) criteria.uniqueResult();
    }
}
