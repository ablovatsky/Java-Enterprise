package by.avectis.contracts.dao.security;

import by.avectis.contracts.dao.DaoException;
import by.avectis.contracts.model.Worker;

import java.util.List;


public interface WorkerDao {

	Worker findWorkerById(Long id) throws DaoException;
	
	Worker findWorkerBySSO(String sso) throws DaoException;
	
	void addWorker(Worker worker) throws DaoException;

	void updateWorker(Worker worker) throws DaoException;
	
	void deleteWorker(Worker worker) throws DaoException;
	
	List<Worker> findAllWorkers() throws DaoException;

}

