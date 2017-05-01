package by.avectis.contracts.service.contract;

import by.avectis.contracts.model.ContractState;
import by.avectis.contracts.service.exception.ServiceException;

import java.util.List;

public interface ContractStateService {

    List<ContractState> findAll();

    ContractState findById(long id)  throws ServiceException;
}
