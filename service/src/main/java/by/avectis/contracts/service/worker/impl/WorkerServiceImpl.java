package by.avectis.contracts.service.worker.impl;

import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.dao.subdivision.SubdivisionDao;
import by.avectis.contracts.dao.worker.WorkerDAO;
import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.model.Worker;
import by.avectis.contracts.service.exception.ServiceException;
import by.avectis.contracts.service.worker.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("workerService")
@Transactional
public class WorkerServiceImpl implements WorkerService {

	@Autowired
	private WorkerDAO workerDAO;

    @Autowired
    private SubdivisionDao subdivisionDao;

	@Autowired
    private PasswordEncoder passwordEncoder;

    @Override
	public Worker findWorkerById(Long id) {
        try {
		    return workerDAO.findWorkerById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
	}

    @Override
	public Worker findWorkerBySSO(String sso) {
        try {
		    return workerDAO.findWorkerBySSO(sso);
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
	}

    @Override
	public void addWorker(Worker worker) {
		worker.setPassword(passwordEncoder.encode(worker.getPassword()));
		try {
			workerDAO.addWorker(worker);
		} catch (DaoException e) {
			throw new ServiceException(e.toString(), e);
		}
	}

    @Override
	public void updateWorker(Worker worker) {
        try {
            Worker entity = workerDAO.findWorkerById(worker.getId());
            if (entity != null) {
                entity.setSsoId(worker.getSsoId());
                if (!worker.getPassword().equals(entity.getPassword())) {
                    entity.setPassword(passwordEncoder.encode(worker.getPassword()));
                }
                entity.setFirstName(worker.getFirstName());
                entity.setLastName(worker.getLastName());
                entity.setEmail(worker.getEmail());
                entity.setWorkerProfiles(worker.getWorkerProfiles());
                entity.setSubdivision(worker.getSubdivision());
            }
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
	}

    @Override
	public void deleteWorkerBySSO(String sso) {
        try {
            Worker worker = workerDAO.findWorkerBySSO(sso);
            if (worker != null) {
                workerDAO.deleteWorker(worker);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
	}

    @Override
	public List<Worker> findAllWorkers(int count, int setNumber, String sortingColumn, int sortingType) {
        try {
            return workerDAO.findAllWorkers(count, setNumber, sortingColumn, sortingType);
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
	}

    @Override
    public List<Worker> findAllWorkersBySubdivisionId(long subdivisionId, int count, int setNumber, String sortingColumn, int sortingType) throws ServiceException {
        try {
            Subdivision subdivision = subdivisionDao.findSubdivisionById(subdivisionId);
            if (subdivision != null) {
                return workerDAO.findAllWorkersBySubdivisionId(subdivision, count, setNumber, sortingColumn, sortingType);
            }
            return null;
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public List<Worker> findAllWorkersByLastName(String lastName, int count, int setNumber, String sortingColumn, int sortingType) throws ServiceException {
        try {
            return workerDAO.findAllWorkersByLastName(lastName, count, setNumber, sortingColumn, sortingType);
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
	public boolean isWorkerSSOUnique(String sso) {
        try {
            Worker user = findWorkerBySSO(sso);
            return  user == null;
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
	}

    @Override
    public long getCountWorkers() throws ServiceException {
        try {
            return workerDAO.getCountWorkers();
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public long getCountWorkers(long subdivisionId) throws ServiceException {
        try {
            Subdivision subdivision = subdivisionDao.findSubdivisionById(subdivisionId);
            if (subdivision != null) {
                return workerDAO.getCountWorkers(subdivision);
            }
            return 0;
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

    @Override
    public long getCountWorkers(String lastName) throws ServiceException {
        try {
            return workerDAO.getCountWorkers(lastName);
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
    }

}
