package by.avectis.contracts.service.laborIntensuty;


import by.avectis.contracts.model.ContractState;
import by.avectis.contracts.model.LaborIntensity;
import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.service.exception.ServiceException;

import java.time.LocalDate;
import java.util.Set;

public interface LaborIntensityService {

    void add(LaborIntensity laborIntensity) throws ServiceException;

    void update(LaborIntensity laborIntensity) throws ServiceException;

    void remove(Long id) throws ServiceException;

    LaborIntensity findById(Long id) throws ServiceException;

    Set<LaborIntensity> findAll();

    Set<LaborIntensity> findAllByContract(long contractId);

    Set<LaborIntensity> findAllBySubdivision(String subdivisionType);

    Set<LaborIntensity> findAllForTimeSheet();

    boolean isLaborIntensityUnique(LaborIntensity laborIntensity);
}
