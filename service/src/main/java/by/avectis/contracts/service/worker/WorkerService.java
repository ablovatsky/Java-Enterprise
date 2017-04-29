package by.avectis.contracts.service.worker;

import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.model.Worker;
import by.avectis.contracts.service.exception.ServiceException;

import java.util.List;
import java.util.Set;


public interface WorkerService {
	
	Worker findById(Long id) throws ServiceException;
	
	Worker findBySSOID(String ssoId);
	
	void add(Worker worker) throws ServiceException;
	
	void update(Worker worker) throws ServiceException;
	
	void removeBySSOID(String sso);

	Set<Worker> findAll(int count, int setNumber, String sortingColumn, int sortingType) throws ServiceException;

	Set<Worker> findAllBySubdivisionId(long subdivisionId, int count, int setNumber, String sortingColumn, int sortingType) throws ServiceException;

	Set<Worker> findAllByLastName(String lastName, int count, int setNumber, String sortingColumn, int sortingType)  throws ServiceException;


    boolean isWorkerSSOIDUnique(String ssoId);

	long getCountRows() throws ServiceException;

    long getCountRowsBySubdivisionId(long subdivisionId) throws ServiceException;

    long getCountRowsByLastName(String lastName) throws ServiceException;

}