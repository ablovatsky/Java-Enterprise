package by.avectis.contracts.service.contract.impl;

import by.avectis.contracts.dao.contracts.ContractDAO;
import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.model.Contract;
import by.avectis.contracts.model.ContractState;
import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.service.contract.ContractService;
import by.avectis.contracts.service.contract.ContractStateService;
import by.avectis.contracts.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

@Service("ContractService")
@Transactional
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractDAO contractDAO;

    @Autowired
    private ContractStateService contractStateService;

    @Override
    public void add(Contract contract) throws ServiceException {
        try {
            contractDAO.add(contract);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public void update(Contract contract) throws ServiceException {
        try {
            Contract entity = contractDAO.findById(contract.getId());
            if (entity != null) {
                entity.setNumber(contract.getNumber());
                entity.setName(contract.getName());
                entity.setDenomination(contract.getDenomination());
                entity.setAxCode(contract.getAxCode());
                entity.setTimeStart(contract.getTimeStart());
                entity.setTimeStop(contract.getTimeStop());
                entity.setCost(contract.getCost());
                entity.setPlannedSurcharge(contract.getPlannedSurcharge());
                entity.setPlannedProfit(contract.getPlannedProfit());
                entity.setContractState(contract.getContractState());
                entity.setCommentFirst(contract.getCommentFirst());
                entity.setCommentSecond(contract.getCommentSecond());
            }
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public void removeById(Long id) throws ServiceException {
        try {
            Contract contract = contractDAO.findById(id);
            if (contract != null) {
                contractDAO.remove(contract);
            }
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public Contract findById(long id) throws ServiceException {
        try {
            return contractDAO.findById(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public Contract findByName(String name) {
        return contractDAO.findByName(name);
}

    @Override
    public Contract findByNumber(String number) {
        return contractDAO.findByNumber(number);
    }

    @Override
    public Contract findByAxCode(String axCode) {
        return contractDAO.findByAxCode(axCode);
    }

    @Override
    public Set<Contract> findAll(int count, int setNumber, String sortingColumn, int sortingType) {
        return contractDAO.findAll(count, setNumber, sortingColumn, sortingType);
    }

    @Override
    public Set<Contract> findAllByNumber(String number, int count, int setNumber, String sortingColumn, int sortingType) {
        return contractDAO.findAllByNumber(number, count, setNumber, sortingColumn, sortingType);
    }

    @Override
    public Set<Contract> findAllByName(String name, int count, int setNumber, String sortingColumn, int sortingType) {
        return contractDAO.findAllByName(name, count, setNumber, sortingColumn, sortingType);
    }

    @Override
    public Set<Contract> findAllByAxCode(String axCode, int count, int setNumber, String sortingColumn, int sortingType) {
        return contractDAO.findAllByAxCode(axCode, count, setNumber, sortingColumn, sortingType);
    }

    @Override
    public Set<Contract> findAllByState(int stateId, int count, int setNumber, String sortingColumn, int sortingType) {
        try {
            ContractState contractState = contractStateService.findById(stateId);
            return contractDAO.findAllByState(contractState, count, setNumber, sortingColumn, sortingType);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public long getCountRows() throws DaoException {
        return contractDAO.getCountRows();
    }

    @Override
    public long getCountRowsByName(String name) {
        return contractDAO.getCountRowsByName(name);
    }

    @Override
    public long getCountRowsByAxCode(String AxCode) {
        return contractDAO.getCountRowsByAxCode(AxCode);
    }

    @Override
    public long getCountRowsByState(ContractState contractState) {
        return contractDAO.getCountRowsByState(contractState);
    }

    @Override
    public boolean isContractNumberUnique(String number) {
        Contract contract = contractDAO.findByNumber(number);
        return contract == null;
    }

    @Override
    public boolean isContractId(long id) {
        Contract contract = contractDAO.findById(id);
        return contract == null;
    }

}
