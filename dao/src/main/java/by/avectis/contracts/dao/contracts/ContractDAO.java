package by.avectis.contracts.dao.contracts;


import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.model.Contract;
import by.avectis.contracts.model.ContractState;

import java.util.List;
import java.util.Set;

public interface ContractDAO {

    void add( Contract contract) throws DaoException;

    void update( Contract contract) throws DaoException;

    void remove( Contract contract) throws DaoException;

    Contract findById(long id) throws DaoException;

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
