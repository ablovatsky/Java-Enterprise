package by.avectis.contracts.service.security;

import by.avectis.contracts.model.Profile;

import java.util.List;


public interface ProfileService {

	Profile findById(Long id);

	Profile findByType(String type);
	
	List<Profile> findAll();
	
}
