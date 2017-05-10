package by.avectis.contracts.service.employment.employmentImpl;

import by.avectis.contracts.dao.contracts.ContractDAO;
import by.avectis.contracts.dao.employment.EmploymentDAO;
import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.dao.worker.WorkerDAO;
import by.avectis.contracts.model.Contract;
import by.avectis.contracts.model.Employment;
import by.avectis.contracts.model.Worker;
import by.avectis.contracts.service.employment.EmploymentService;
import by.avectis.contracts.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

@Service("EmploymentService")
@Transactional
public class EmploymentServiceImpl implements EmploymentService{

    @Autowired
    private EmploymentDAO employmentDAO;

    @Autowired
    private WorkerDAO workerDAO;

    @Autowired
    private ContractDAO contractDAO;

    @Override
    public void add(Employment employment) throws ServiceException {
        try {
            employmentDAO.add(employment);
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public void update(Employment employment) throws ServiceException {
        try {
            Employment entity = employmentDAO.findById(employment.getId());
            if (entity != null) {
                entity.setContract(employment.getContract());
                entity.setDate(employment.getDate());
                entity.setHoursWorked(employment.getHoursWorked());
                entity.setTimeSheet(employment.getTimeSheet());
                entity.setWorker(employment.getWorker());
            }
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public void remove(long id) throws ServiceException {
        try {
            Employment entity = employmentDAO.findById(id);
            if (entity != null) {
                employmentDAO.remove(entity);
            }
        }  catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public Employment findById(long id) throws ServiceException {
        try {
            return employmentDAO.findById(id);

        }  catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public Set<Employment> findByWorker(String ssoId) {
        try {
            Worker worker = workerDAO.findBySSOID(ssoId);
            if (worker != null) {
                return employmentDAO.findByWorker(worker);
            }
            return null;
        }  catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public Set<Employment> findByContract(String contractNumber) {
        try {
            Contract contract = contractDAO.findByNumber(contractNumber);
            if (contract != null) {
                return employmentDAO.findByContract(contract);
            }
            return null;
        }  catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
    }
}
