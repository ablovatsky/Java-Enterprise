package by.avectis.contracts.service.employment;

import by.avectis.contracts.model.Employment;
import by.avectis.contracts.service.exception.ServiceException;

import java.time.LocalDate;
import java.util.Set;

public interface EmploymentService {

    void add(Employment employment) throws ServiceException;

    void update(Employment employment) throws ServiceException;

    void remove(long id) throws ServiceException;

    Employment findById(long id) throws ServiceException;

    Set<Employment> findByWorker(String ssoId);

    Set<Employment> findByContract(String contractNumber);
}
