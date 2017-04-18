package by.avectis.contracts.dao.worker;

import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.model.Worker;

import java.util.List;


public interface WorkerDAO {

	Worker findWorkerById(Long id) throws DaoException;
	
	Worker findWorkerBySSO(String sso) throws DaoException;
	
	void addWorker(Worker worker) throws DaoException;

	void updateWorker(Worker worker) throws DaoException;
	
	void deleteWorker(Worker worker) throws DaoException;
	
	List<Worker> findAllWorkers() throws DaoException;

}

