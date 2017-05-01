package by.avectis.contracts.dto.contract;

import by.avectis.contracts.dto.contract.modelDTO.ShortInfoContract;
import by.avectis.contracts.model.Contract;
import by.avectis.contracts.service.contract.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class ContractDTO {

    @Autowired
    private ContractService contractService;

    private Set<ShortInfoContract> contracts;

    private void setContracts(int count, int setNumber, String sortingColumn, int sortingType) {
        contracts = new LinkedHashSet<>();
        Set<Contract> contractSet = contractService.findAll(count, setNumber, sortingColumn, sortingType);
        contractSet.forEach(contract -> contracts.add(new ShortInfoContract(contract)));
    }

    private void setContractsByNumber(String number, int count, int setNumber, String sortingColumn, int sortingType) {
        contracts = new LinkedHashSet<>();
        Set<Contract> contractSet = contractService.findAllByNumber(number, count, setNumber, sortingColumn, sortingType);
        contractSet.forEach(contract -> contracts.add(new ShortInfoContract(contract)));
    }

    private void setContractsByState(int state, int count, int setNumber, String sortingColumn, int sortingType) {
        contracts = new LinkedHashSet<>();
        Set<Contract> contractSet = contractService.findAllByState(state, count, setNumber, sortingColumn, sortingType);
        contractSet.forEach(contract -> contracts.add(new ShortInfoContract(contract)));
    }

    public Set<ShortInfoContract> getAll(int count, int setNumber, String sortingColumn, int sortingType) {
        setContracts(count, setNumber, sortingColumn, sortingType);
        return contracts;
    }

    public Set<ShortInfoContract> getByNumber(String number, int count, int setNumber, String sortingColumn, int sortingType) {
        setContractsByNumber(number, count, setNumber, sortingColumn, sortingType);
        return contracts;
    }

    public Set<ShortInfoContract> getByState(int state, int count, int setNumber, String sortingColumn, int sortingType) {
        setContractsByState(state, count, setNumber, sortingColumn, sortingType);
        return contracts;
    }
}
