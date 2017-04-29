package by.avectis.contracts.dao.contracts.impl;


import by.avectis.contracts.dao.AbstractDAO;
import by.avectis.contracts.dao.Util.CriteriaBuilder;
import by.avectis.contracts.dao.contracts.ContractStateDAO;
import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.model.ContractState;
import org.hibernate.Criteria;
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
        return (List<ContractState>) criteria.list();
    }

    @Override
    public ContractState findById(long id) throws DaoException{
        return getById(id);
    }
}
