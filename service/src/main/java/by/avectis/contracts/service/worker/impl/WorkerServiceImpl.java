package by.avectis.contracts.service.worker.impl;

import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.dao.worker.WorkerDAO;
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
	private WorkerDAO dao;

	@Autowired
    private PasswordEncoder passwordEncoder;

    @Override
	public Worker findWorkerById(Long id) {
        try {
		    return dao.findWorkerById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
	}

    @Override
	public Worker findWorkerBySSO(String sso) {
        try {
		    return dao.findWorkerBySSO(sso);
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
	}

    @Override
	public void addWorker(Worker worker) {
		worker.setPassword(passwordEncoder.encode(worker.getPassword()));
		try {
			dao.addWorker(worker);
		} catch (DaoException e) {
			throw new ServiceException(e.toString(), e);
		}
	}

    @Override
	public void updateWorker(Worker worker) {
        if (worker != null) {
            worker.setPassword(passwordEncoder.encode(worker.getPassword()));
            try {
                dao.updateWorker(worker);
            } catch (DaoException e) {
                throw new ServiceException(e.toString(), e);
            }
        }
	}

    @Override
	public void deleteWorkerBySSO(String sso) {
        try {
            Worker worker = dao.findWorkerBySSO(sso);
            if (worker != null) {
                dao.deleteWorker(worker);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.toString(), e);
        }
	}

    @Override
	public List<Worker> findAllWorkers() {
        try {
            return dao.findAllWorkers();
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
	
}
