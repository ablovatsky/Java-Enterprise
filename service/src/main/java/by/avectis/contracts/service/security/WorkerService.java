package by.avectis.contracts.service.security;

import by.avectis.contracts.dao.DaoException;
import by.avectis.contracts.model.Worker;
import by.avectis.contracts.service.ServiceException;

import java.util.List;


public interface WorkerService {
	
	Worker findWorkerById(Long id) throws ServiceException;
	
	Worker findWorkerBySSO(String sso) throws ServiceException;
	
	void addWorker(Worker worker) throws ServiceException;
	
	void updateWorker(Worker worker) throws ServiceException;
	
	void deleteWorkerBySSO(String sso) throws ServiceException;

	List<Worker> findAllWorkers() throws ServiceException;
	
	boolean isWorkerSSOUnique(String sso) throws ServiceException;

}