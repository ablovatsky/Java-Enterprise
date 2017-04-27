package by.avectis.contracts.dao.worker;

import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.model.Worker;

import java.util.List;


public interface WorkerDAO {

	Worker findWorkerById(Long id) throws DaoException;
	
	Worker findWorkerBySSO(String sso) throws DaoException;
	
	void addWorker(Worker worker) throws DaoException;

	void updateWorker(Worker worker) throws DaoException;
	
	void deleteWorker(Worker worker) throws DaoException;
	
	List<Worker> findAllWorkers(int count, int setNumber, String sortingColumn, int sortingType) throws DaoException;

	List<Worker> findAllWorkersBySubdivisionId(Subdivision subdivision, int count, int setNumber, String sortingColumn, int sortingType)  throws DaoException;

	List<Worker> findAllWorkersByLastName(String lastName, int count, int setNumber, String sortingColumn, int sortingType)  throws DaoException;

	long getCountWorkers() throws DaoException;

	long getCountWorkers(Subdivision subdivision) throws DaoException;

	long getCountWorkers(String lastName) throws DaoException;

}

