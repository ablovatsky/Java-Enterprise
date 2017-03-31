package by.pvt.restaurants.service.security;

import by.pvt.restaurants.model.Profile;

import java.util.List;


public interface ProfileService {

	Profile findById(int id);

	Profile findByType(String type);
	
	List<Profile> findAll();
	
}
