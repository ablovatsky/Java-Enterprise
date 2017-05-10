package by.avectis.contracts.dao.laborIntensity.laborIntensityImpl;

import by.avectis.contracts.dao.AbstractDAO;
import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.dao.laborIntensity.LaborIntensityDAO;
import by.avectis.contracts.model.Contract;
import by.avectis.contracts.model.ContractState;
import by.avectis.contracts.model.LaborIntensity;
import by.avectis.contracts.model.Subdivision;
import org.apache.tools.ant.types.resources.Restrict;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Repository("LaborIntensity")
public class LaborIntensityDAOImpl extends AbstractDAO<Long, LaborIntensity> implements LaborIntensityDAO{
    @Override
    public void add(LaborIntensity laborIntensity) throws DaoException {
        persistEntity(laborIntensity);
    }

    @Override
    public void update(LaborIntensity laborIntensity) throws DaoException {
        updateEntity(laborIntensity);
    }

    @Override
    public void remove(LaborIntensity laborIntensity) throws DaoException {
        deleteEntity(laborIntensity);
    }

    @Override
    public LaborIntensity findById(Long id) throws DaoException {
        return getById(id);
    }

    @Override
    public LaborIntensity findByContractAndSubdivision(Contract contract, Subdivision subdivision) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("contract", contract));
        criteria.add(Restrictions.eq("subdivision", subdivision));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return (LaborIntensity) criteria.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<LaborIntensity> findAll() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return new LinkedHashSet<>((List<LaborIntensity>) criteria.list());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<LaborIntensity> findAllByContract(Contract contract) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("contract", contract));
        criteria.setResultTransformer((Criteria.DISTINCT_ROOT_ENTITY));
        return new LinkedHashSet<>((List<LaborIntensity>) criteria.list());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<LaborIntensity> findAllBySubdivision(Subdivision subdivision) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("subdivision", subdivision));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return new LinkedHashSet<>((List<LaborIntensity>) criteria.list());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<LaborIntensity> findAllForTimeSheet(Subdivision subdivision, LocalDate date, ContractState state) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("subdivision", subdivision));/*
        criteria.add(Restrictions.lt("contract.timeStop", date));*/
        criteria.createAlias("contract", "contracts");
        criteria.createAlias("contracts.contractState", "contracts_state");
        criteria.add(Restrictions.eq("contracts_state.id", state.getId()));
        return new LinkedHashSet<>((List<LaborIntensity>) criteria.list());
    }
}
