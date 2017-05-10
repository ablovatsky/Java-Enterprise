package by.avectis.contracts.dao.employment;


import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.model.Contract;
import by.avectis.contracts.model.Employment;
import by.avectis.contracts.model.TimeSheet;
import by.avectis.contracts.model.Worker;

import java.time.LocalDate;
import java.util.Set;

public interface EmploymentDAO {

    void add(Employment employment) throws DaoException;

    void update(Employment employment) throws DaoException;

    void remove(Employment employment) throws DaoException;

    Employment findById(long id) throws DaoException;

    Set<Employment> findByWorker(Worker worker);

    Set<Employment> findByContract(Contract contract);

    Set<Employment> findByTimeSheet(TimeSheet timeSheet);
}
