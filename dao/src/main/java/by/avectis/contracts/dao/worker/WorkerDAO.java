package by.avectis.contracts.dao.worker;

import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.model.Subdivision;
import by.avectis.contracts.model.Worker;
import java.util.Set;


public interface WorkerDAO {

	Worker findById(Long id) throws DaoException;
	
	Worker findBySSOID(String ssoId);
	
	void add(Worker worker) throws DaoException;

	void update(Worker worker) throws DaoException;
	
	void remove(Worker worker) throws DaoException;
	
	Set<Worker> findAll(int count, int setNumber, String sortingColumn, int sortingType);

	Set<Worker> findAllBySubdivision(Subdivision subdivision);

	Set<Worker> findAllBySubdivision(Subdivision subdivision, int count, int setNumber, String sortingColumn, int sortingType);

	Set<Worker> findAllByLastName(String lastName, int count, int setNumber, String sortingColumn, int sortingType);

	long getCountRows();

	long getCountRowsBySubdivisionId(Subdivision subdivision);

	long getCountRowsByLastName(String lastName);

}

