package by.avectis.contracts.dao.security;

import by.avectis.contracts.model.Profile;

import java.util.List;


public interface ProfileDao {

	List<Profile> findAll();
	
	Profile findByType(String type);
	
	Profile findById(Long id);
}
