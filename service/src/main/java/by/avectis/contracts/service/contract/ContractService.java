package by.avectis.contracts.service.contract;


import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.model.Contract;
import by.avectis.contracts.model.ContractState;
import by.avectis.contracts.service.exception.ServiceException;

import java.util.Set;

public interface ContractService {
    void add( Contract contract) throws ServiceException;

    void update( Contract contract) throws ServiceException;

    void removeById(Long id) throws ServiceException;

    Contract findById(long id) throws ServiceException;

    Contract findByName(String name);

    Contract findByAxCode(String axCode);

    Set<Contract> findAll(int count, int setNumber, String sortingColumn, int sortingType);

    Set<Contract> findAllByName(String name, int count, int setNumber, String sortingColumn, int sortingType);

    Set<Contract> findAllByAxCode(String axCode, int count, int setNumber, String sortingColumn, int sortingType);

    Set<Contract> findAllByState(ContractState contractState, int count, int setNumber, String sortingColumn, int sortingType);

    long getCountRows() throws DaoException;

    long getCountRowsByName(String name);

    long getCountRowsByAxCode(String axCode);

    long getCountRowsByState(ContractState contractState);
}
