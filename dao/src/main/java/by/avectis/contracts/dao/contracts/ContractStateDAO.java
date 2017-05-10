package by.avectis.contracts.dao.contracts;


import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.model.ContractState;

import java.util.List;

public interface ContractStateDAO {

    List<ContractState> findAll();

    ContractState findById(long id)  throws DaoException;

    ContractState findByName(String nameType);

}
