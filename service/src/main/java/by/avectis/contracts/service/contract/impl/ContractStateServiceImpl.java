package by.avectis.contracts.service.contract.impl;

import by.avectis.contracts.dao.contracts.ContractStateDAO;
import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.model.ContractState;
import by.avectis.contracts.service.contract.ContractStateService;
import by.avectis.contracts.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ContractStateService")
@Transactional
public class ContractStateServiceImpl implements ContractStateService {

    @Autowired
    private ContractStateDAO contractStateDAO;

    @Override
    public List<ContractState> findAll() {
        return contractStateDAO.findAll();
    }

    @Override
    public ContractState findById(long id){
        try {
            return contractStateDAO.findById(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.toString(), e);
        }
    }
}
