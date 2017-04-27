package by.avectis.contracts.service.worker;

import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.model.Worker;
import by.avectis.contracts.service.exception.ServiceException;

import java.util.List;


public interface WorkerService {
	
	Worker findWorkerById(Long id) throws ServiceException;
	
	Worker findWorkerBySSO(String sso) throws ServiceException;
	
	void addWorker(Worker worker) throws ServiceException;
	
	void updateWorker(Worker worker) throws ServiceException;
	
	void deleteWorkerBySSO(String sso) throws ServiceException;

	List<Worker> findAllWorkers(int count, int setNumber, String sortingColumn, int sortingType) throws ServiceException;

	List<Worker> findAllWorkersBySubdivisionId(long subdivisionId, int count, int setNumber, String sortingColumn, int sortingType) throws ServiceException;

    List<Worker> findAllWorkersByLastName(String lastName, int count, int setNumber, String sortingColumn, int sortingType)  throws ServiceException;


    boolean isWorkerSSOUnique(String sso) throws ServiceException;

	long getCountWorkers() throws ServiceException;

    long getCountWorkers(long subdivisionId) throws ServiceException;

    long getCountWorkers(String lastName) throws ServiceException;

}