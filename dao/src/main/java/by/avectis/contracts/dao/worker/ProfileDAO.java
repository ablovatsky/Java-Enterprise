package by.avectis.contracts.dao.worker;

import by.avectis.contracts.model.Profile;

import java.util.List;


public interface ProfileDAO {

	List<Profile> findAll();
	
	Profile findByType(String type);
	
	Profile findById(Long id);
}
