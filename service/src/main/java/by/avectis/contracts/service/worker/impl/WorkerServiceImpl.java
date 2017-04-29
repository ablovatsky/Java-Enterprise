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
import java.util.Set;

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
	public Worker findById(Long id) {
        try {
		    return workerDAO.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
	}

    @Override
	public Worker findBySSOID(String ssoId) {
        return workerDAO.findBySSOID(ssoId);
	}

    @Override
	public void add(Worker worker) {
		worker.setPassword(passwordEncoder.encode(worker.getPassword()));
		try {
			workerDAO.add(worker);
		} catch (DaoException e) {
			throw new ServiceException(e.toString(), e);
		}
	}

    @Override()
	public void update(Worker worker) {
        try {
            Worker entity = workerDAO.findById(worker.getId());
            if (entity != null) {
                entity.setSsoId(worker.getSsoId());
                if (!worker.getPassword().equals(entity.getPassword())) {
                    entity.setPassword(passwordEncoder.encode(worker.getPassword()));
                }
                entity.setFirstName(worker.getFirstName());
                entity.setLastName(worker.getLastName());
                entity.setPatronymic(worker.getPatronymic());
                entity.setEmail(worker.getEmail());
                entity.setWorkerProfiles(worker.getWorkerProfiles());
                entity.setSubdivision(worker.getSubdivision());
            }
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
	}

    @Override
	public void removeBySSOID(String ssoId) {
        Worker worker = workerDAO.findBySSOID(ssoId);
        if (worker != null) {
            workerDAO.remove(worker);
        }
	}

    @Override
	public Set<Worker> findAll(int count, int setNumber, String sortingColumn, int sortingType) {
         return workerDAO.findAll(count, setNumber, sortingColumn, sortingType);
	}

    @Override
    public Set<Worker> findAllBySubdivisionId(long subdivisionId, int count, int setNumber, String sortingColumn, int sortingType) {
        Subdivision subdivision = subdivisionDao.findById(subdivisionId);
        if (subdivision != null) {
            return workerDAO.findAllBySubdivisionId(subdivision, count, setNumber, sortingColumn, sortingType);
        }
        return null;
    }

    @Override
    public Set<Worker> findAllByLastName(String lastName, int count, int setNumber, String sortingColumn, int sortingType) {
        return workerDAO.findAllByLastName(lastName, count, setNumber, sortingColumn, sortingType);
    }

    @Override
	public boolean isWorkerSSOIDUnique(String ssoId) {
        Worker user = findBySSOID(ssoId);
        return  user == null;
	}

    @Override
    public long getCountRows() {
        return workerDAO.getCountRows();
    }

    @Override
    public long getCountRowsBySubdivisionId(long subdivisionId) {
        Subdivision subdivision = subdivisionDao.findById(subdivisionId);
        if (subdivision != null) {
            return workerDAO.getCountRowsBySubdivisionId(subdivision);
        }
        return 0;
    }

    @Override
    public long getCountRowsByLastName(String lastName) {
        return workerDAO.getCountRowsByLastName(lastName);
    }

}
