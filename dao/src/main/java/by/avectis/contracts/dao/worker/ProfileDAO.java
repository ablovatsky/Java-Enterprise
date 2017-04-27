package by.avectis.contracts.dao.worker;

import by.avectis.contracts.dao.exception.DaoException;
import by.avectis.contracts.model.Profile;

import java.util.List;


public interface ProfileDAO {

	List<Profile> findAll()  throws DaoException;
	
	Profile findByType(String type)  throws DaoException ;
	
	Profile findById(Long id)  throws DaoException ;
}
